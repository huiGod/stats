package cn._51app.stats.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn._51app.stats.base.BaseOcs;
import cn._51app.stats.dao.LogAndroidDao;
import cn._51app.stats.domain.Adevice;
import cn._51app.stats.domain.AdeviceDay;
import cn._51app.stats.util.DateUtil;
import cn._51app.stats.util.LoggerUtils;

public class LogAndroidServiceImpl extends BaseOcs implements LogAndroidService {
	private LogAndroidDao logAndroidDao;
	
	public LogAndroidDao getLogAndroidDao() {
		return logAndroidDao;
	}

	public void setLogAndroidDao(LogAndroidDao logAndroidDao) {
		this.logAndroidDao = logAndroidDao;
	}

	@Override
	public void saveAndroidDevice(Adevice adevice) {
		// 记录设备访问日志到日志文件
		LoggerUtils.logAndroidDeviceAction(adevice);
		if (StringUtils.isEmpty(adevice.getImei())
				|| StringUtils.isEmpty(adevice.getModel())
				|| StringUtils.isEmpty(adevice.getAndroid())
				|| StringUtils.isEmpty(adevice.getVersion())
				|| adevice.getType() < 1) {
			return;
		}
		int cid = adevice.getCid(), model = 0, android = 0, version = 0;
		// 设备型号列表
		String key = "am_k";  
		List<Map<String, Object>> models = (List<Map<String, Object>>) get(key);
		if (models == null) {
			models = logAndroidDao.getModels();
			// 设置缓存
			set(key, models, 0);
		}
		for (Map<String, Object> m : models) {
			if (((String) m.get("model")).equals(adevice.getModel())) {
				model = (Integer) m.get("id");
				break;
			}
		}
		if (model == 0) {// 保存设备型号信息
			model = logAndroidDao
					.saveDeviceModel(adevice.getModel(), adevice.getModel());
			// 删除缓存
			delete(key);
		}
		// android系统版本列表
		key = "ai_k";
		List<Map<String, Object>> iosVersions = (List<Map<String, Object>>) get(key);
		if (iosVersions == null) {
			iosVersions = logAndroidDao.getAndroidVersions();
			// 设置缓存
			set(key, iosVersions, 0);
		}
		for (Map<String, Object> iosVersion : iosVersions) {
			if (((String) iosVersion.get("code")).equals(adevice.getAndroid())) {
				android = (Integer) iosVersion.get("id");
				break;
			}
		}
		if (android == 0) {// 保存android版本信息
			android = logAndroidDao.saveAndroidVersion(adevice.getAndroid());
			// 删除缓存
			delete(key);
		}
		// 应用版本列表
		key = "av_k_" + adevice.getType();
		List<Map<String, Object>> versions = (List<Map<String, Object>>) get(key);
		if (versions == null) {
			versions = logAndroidDao.getVersions(adevice.getType());
			// 设置缓存
			set(key, versions, 0);
		}
		for (Map<String, Object> v : versions) {
			if (((String) v.get("number")).equals(adevice.getVersion())) {
				version = (Integer) v.get("id");
				break;
			}
		}
		if (version == 0) {// 保存安卓应用版本信息
			version = logAndroidDao.saveVersion(adevice.getVersion(), adevice.getType());
			// 删除缓存
			delete(key);
		}
		AdeviceDay adeviceDay = new AdeviceDay();
		adeviceDay.setImei(adevice.getImei());
		adeviceDay.setType(adevice.getType());
		String today = DateUtil.date2String(new Date(), "yyyy-MM-dd");
		// 查询设备是否存在
		Map<String, Object> d = logAndroidDao.getDevice(adeviceDay);
		if (d != null) {// 设备之前存在
			// 修改设备信息
			adeviceDay.setId((Integer) d.get("id"));
			adeviceDay.setCid(cid);
			adeviceDay.setModel(model);
			adeviceDay.setAndroid(android);
			adeviceDay.setVersion(version);
			logAndroidDao.updateDevice(adeviceDay);
			if (!today.equals(DateUtil.date2String((Date) d.get("updateTime"),
					"yyyy-MM-dd"))) {// 当天没有算过活跃
				// 修改留存信息
				adeviceDay.setCid((Integer) d.get("cid"));
				adeviceDay.setModel((Integer) d.get("model"));
				adeviceDay.setAndroid((Integer) d.get("android"));
				adeviceDay.setVersion((Integer) d.get("version"));
				adeviceDay.setCreateDay(DateUtil.date2String(
						(Date) d.get("createTime"), "yyyy-MM-dd"));
				long dayInterval = (DateUtil.formatDate(new Date(),   //上次登录的时间距离现在的间隔
						"yyyy-MM-dd").getTime() - DateUtil.formatDate(
						(Date) d.get("createTime"), "yyyy-MM-dd").getTime())
						/ (24 * 60 * 60 * 1000);
				switch (new Long(dayInterval).intValue()) {
				case 1:// 1天后登录
					adeviceDay.set_1dayRetention(1);
					logAndroidDao.updateDeviceRetention(adeviceDay);
					break;
				case 2:// 2天后登录
					adeviceDay.set_2dayRetention(1);
					logAndroidDao.updateDeviceRetention(adeviceDay);
					break;
				case 3:// 3天后登录
					adeviceDay.set_3dayRetention(1);
					logAndroidDao.updateDeviceRetention(adeviceDay);
					break;
				case 7:// 7天后登录
					adeviceDay.set_7dayRetention(1);
					logAndroidDao.updateDeviceRetention(adeviceDay);
					break;
				case 14:// 14天后登录
					adeviceDay.set_14dayRetention(1);
					logAndroidDao.updateDeviceRetention(adeviceDay);
					break;
				case 30:// 30天后登录
					adeviceDay.set_30dayRetention(1);
					logAndroidDao.updateDeviceRetention(adeviceDay);
					break;
				default:
					break;
				}
				// 活跃用户+1
				adeviceDay.setActive(1);
				adeviceDay.setCreateDay(today); //将时间更新到今天    同一个用户会因为时间不同  产生多条 记录
				// 保存设备数据统计
				logAndroidDao.saveOrUpdateDeviceDay(adeviceDay);
			}
		} else {
			// 保存设备信息
			adeviceDay.setCid(cid);
			adeviceDay.setModel(model);
			adeviceDay.setAndroid(android);
			adeviceDay.setVersion(version);
			logAndroidDao.saveDevice(adeviceDay);
			// 新用户+1
			adeviceDay.set_new(1);
			// 活跃用户+1
			adeviceDay.setActive(1);
			adeviceDay.setCreateDay(today);
			// 保存设备数据统计
			logAndroidDao.saveOrUpdateDeviceDay(adeviceDay);
		}
	}
	
	@Override
	public void deleteAndroidVersionCache() {
		String key = "ai_k";
		delete(key);
	}
	
	@Override
	public void deleteChannelCache() {
		String key = "ac_k";
		delete(key);
	}
	
	@Override
	public void deleteModelCache() {
		String key = "am_k";
		delete(key);
	}
	
	@Override
	public void deleteVersionCache(int type) {
		String key = "av_k_" + type;
		delete(key);
	}
}

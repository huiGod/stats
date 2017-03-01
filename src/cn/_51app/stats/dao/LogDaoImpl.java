package cn._51app.stats.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import cn._51app.stats.base.BaseDao;
import cn._51app.stats.domain.DeviceDay;

/**
 * 和数据统计相关的数据库处理
 * 
 * @author jonny
 * 
 */
public class LogDaoImpl extends BaseDao implements LogDao {

	private JdbcTemplate statsJdbc;

	public JdbcTemplate getStatsJdbc() {
		return statsJdbc;
	}

	public void setStatsJdbc(JdbcTemplate statsJdbc) {
		this.statsJdbc = statsJdbc;
	}

	/**
	 * 得到设备信息
	 * 
	 * @param device
	 * @return
	 */
	@Override
	public Map<String, Object> getDevice(DeviceDay device) {
		String sql = "SELECT id, uuid, cid, model, ios, version, create_time AS createTime, update_time AS updateTime, type FROM t_device WHERE uuid = ? AND type = ?";
		return this.queryForMap(statsJdbc, sql, device.getUuid(),
				device.getType());
	}

	/**
	 * 保存设备信息
	 * 
	 * @param device
	 * @return
	 */
	@Override
	public int saveDevice(DeviceDay device) {
		String sql = "INSERT INTO t_device(uuid, cid, model, ios, version, create_time, update_time, type) VALUES (?, ?, ?, ?, ?, NOW(), NOW(), ?)";
		return this.saveRetKey(
				statsJdbc,
				sql,
				new Object[] { device.getUuid(), device.getCid(),
						device.getModel(), device.getIos(),
						device.getVersion(), device.getType() }, new int[] {
						Types.VARCHAR, Types.INTEGER, Types.INTEGER,
						Types.INTEGER, Types.INTEGER, Types.INTEGER });
	}

	/**
	 * 修改设备信息
	 * 
	 * @param device
	 */
	@Override
	public void updateDevice(DeviceDay device) {
		String sql = "UPDATE t_device SET cid = ?, model = ?, ios = ?, version = ?, update_time = NOW() WHERE id = ?";
		this.update(
				statsJdbc,
				sql,
				new Object[] { device.getCid(), device.getModel(),
						device.getIos(), device.getVersion(), device.getId() });
	}

	/**
	 * 保存设备每天的数据统计
	 * 
	 * @param deviceDay
	 */
	@Override
	public void saveOrUpdateDeviceDay(DeviceDay deviceDay) {
		String sql = "INSERT INTO t_device_day(cid, model, ios, version, create_day, type, new, active) VALUES (?, ?, ?, ?, ?, ?, ?, ?)  ON DUPLICATE KEY UPDATE new = new + ?, active = active + ?";
		this.update(
				statsJdbc,
				sql,
				new Object[] { deviceDay.getCid(), deviceDay.getModel(),
						deviceDay.getIos(), deviceDay.getVersion(),
						deviceDay.getCreateDay(), deviceDay.getType(),
						deviceDay.get_new(), deviceDay.getActive(),
						deviceDay.get_new(), deviceDay.getActive() });
	}

	/**
	 * 修改设备留存
	 * 
	 * @param deviceDay
	 */
	@Override
	public void updateDeviceRetention(DeviceDay deviceDay) {
		String sql = "UPDATE t_device_day SET 1day_retention = 1day_retention + ?, 2day_retention = 2day_retention + ?, 3day_retention = 3day_retention + ?, 7day_retention = 7day_retention + ?, 14day_retention = 14day_retention + ?, 30day_retention = 30day_retention + ? WHERE cid = ? AND model = ? AND ios = ? AND version = ? AND create_day = ? AND type = ?";
		this.update(
				statsJdbc,
				sql,
				new Object[] { deviceDay.get_1dayRetention(),
						deviceDay.get_2dayRetention(),
						deviceDay.get_3dayRetention(),
						deviceDay.get_7dayRetention(),
						deviceDay.get_14dayRetention(),
						deviceDay.get_30dayRetention(), deviceDay.getCid(),
						deviceDay.getModel(), deviceDay.getIos(),
						deviceDay.getVersion(), deviceDay.getCreateDay(),
						deviceDay.getType() });
	}

	/**
	 * 得到所有渠道
	 * 
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getChannels() {
		String sql = "SELECT id FROM t_channel ORDER BY id ASC LIMIT 1000";
		return this.statsJdbc.queryForList(sql);
	}

	/**
	 * 得到所有设备型号
	 * 
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getModels() {
		String sql = "SELECT id, model FROM t_device_model LIMIT 1000";
		return this.statsJdbc.queryForList(sql);
	}

	/**
	 * 得到所有ios版本
	 * 
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getIosVersions() {
		String sql = "SELECT id, code FROM t_ios_version LIMIT 1000";
		return this.statsJdbc.queryForList(sql);
	}

	/**
	 * 得到应用版本号
	 * 
	 * @param type
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getVersions(int type) {
		String sql = "SELECT id, number FROM t_version WHERE type = ? LIMIT 1000";
		return this.statsJdbc.queryForList(sql, type);
	}

	/**
	 * 保存设备型号
	 * 
	 * @param mobile
	 * @param model
	 * @return
	 */
	@Override
	public int saveDeviceModel(String mobile, String model) {
		String sql = "INSERT INTO t_device_model(mobile, model) VALUES (?, ?)";
		return this.saveRetKey(statsJdbc, sql, new Object[] { mobile, model },
				new int[] { Types.VARCHAR, Types.VARCHAR });
	}

	/**
	 * 保存ios版本
	 * 
	 * @param code
	 * @return
	 */
	@Override
	public int saveIosVersion(String code) {
		String sql = "INSERT INTO t_ios_version(code) VALUES (?)";
		return this.saveRetKey(statsJdbc, sql, new Object[] { code },
				new int[] { Types.INTEGER });
	}

	/**
	 * 保存应用版本
	 * 
	 * @param number
	 * @param type
	 * @return
	 */
	@Override
	public int saveVersion(String number, int type) {
		String sql = "INSERT INTO t_version(number, type) VALUES (?, ?)";
		return this.saveRetKey(statsJdbc, sql, new Object[] { number, type },
				new int[] { Types.VARCHAR, Types.INTEGER });
	}

	/**
	 * tengh 2016年2月27日 下午4:30:51
	 * @param appid
	 * @return
	 * TODO 查询cpa标识
	 */
	@Override
	public Map<String, Object> getCpaAppId(String appid) {
		try {
			String sql="SELECT `id`,`appid`,`url`,`flag_url` AS `flagUrl`,`param`,`status` FROM `cpa_appid` WHERE `appid`=?";
			return this.statsJdbc.queryForMap(sql,new Object[]{appid});
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * tengh 2016年2月27日 下午4:42:30
	 * @param appid
	 * @param idfa
	 * @param os
	 * @param channel
	 * TODO 保存cpa信息
	 */
	@Override
	public int insertCpaInfo(Integer appid, String idfa, String os, Integer channel,String ip,String callback) {
		String sql="INSERT INTO `cpa_idfa` (`appid`,`idfa`,`time`,`os`,`channel`,`ip`,`callback`) VALUES(?,?,NOW(),?,?,?,?) ON DUPLICATE KEY UPDATE `count`=`count`+1,`ip`=?,`callback`=?";
		return update(statsJdbc, sql, new Object[]{appid,idfa,os,channel,ip,callback,ip,callback});
	}
}

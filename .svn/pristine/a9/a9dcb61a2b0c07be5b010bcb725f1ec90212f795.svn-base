package cn._51app.stats.dao;

import java.util.List;
import java.util.Map;

import cn._51app.stats.domain.DeviceDay;

public interface LogDao {

	/**
	 * 得到设备信息
	 * 
	 * @param device
	 * @return
	 */
	public Map<String, Object> getDevice(DeviceDay device);
	
	/**
	 * 保存设备信息
	 * 
	 * @param device
	 * @return
	 */
	public int saveDevice(DeviceDay device);
	
	/**
	 * 修改设备信息
	 * 
	 * @param device
	 */
	public void updateDevice(DeviceDay device);
	
	/**
	 * 保存设备每天的数据统计
	 * 
	 * @param deviceDay
	 */
	public void saveOrUpdateDeviceDay(DeviceDay deviceDay);
	
	/**
	 * 修改设备留存
	 * 
	 * @param deviceDay
	 */
	public void updateDeviceRetention(DeviceDay deviceDay);
	
	/**
	 * 得到所有渠道
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getChannels();
	
	/**
	 * 得到所有设备型号
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getModels();
	
	/**
	 * 得到所有ios版本
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getIosVersions();
	
	/**
	 * 得到应用版本号
	 * 
	 * @param type
	 * @return
	 */
	public List<Map<String, Object>> getVersions(int type);
	
	/**
	 * 保存设备型号
	 * 
	 * @param mobile
	 * @param model
	 * @return
	 */
	public int saveDeviceModel(String mobile, String model);
	
	/**
	 * 保存ios版本
	 * 
	 * @param code
	 * @return
	 */
	public int saveIosVersion(String code);
	
	/**
	 * 保存应用版本
	 * 
	 * @param number
	 * @param type
	 * @return
	 */
	public int saveVersion(String number, int type);

	/**
	 * tengh 2016年2月27日 下午4:29:57
	 * @param appid
	 * @return
	 * TODO
	 */
	public Map<String, Object> getCpaAppId(String appid);

	/**
	 * tengh 2016年2月27日 下午4:39:01
	 * @param appid_id
	 * @param idfa
	 * @param os
	 * @param channel
	 * TODO 保存
	 * @param ip 
	 * @param callback 
	 */
	public int insertCpaInfo(Integer appid_id, String idfa, String os, Integer channel, String ip, String callback);

}

package cn._51app.stats.service;

import cn._51app.stats.domain.Device;

public interface LogService {

	/**
	 * 保存设备日志和统计数据
	 * 
	 * @param device
	 */
	public void saveDevice(Device device);
	
	/**
	 * 删除渠道缓存
	 * 
	 */
	public void deleteChannelCache();
	
	/**
	 * 删除设备型号缓存
	 * 
	 */
	public void deleteModelCache();
	
	/**
	 * 删除ios系统版本缓存
	 * 
	 */
	public void deleteIosVersionCache();
	
	/**
	 * 删除应用版本缓存
	 * 
	 */
	public void deleteVersionCache(int type);

	/**
	 * tengh 2016年2月27日 下午4:26:22
	 * @param appid
	 * @param idfa
	 * @param os
	 * @param channel
	 * TODO cpa来源分发统计
	 * @param ip 
	 * @param callback 
	 */
	public void saveDevice(String appid, String idfa, String os, Integer channel, String ip, String callback);
}

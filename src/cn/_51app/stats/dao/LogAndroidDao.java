package cn._51app.stats.dao;

import java.util.List;
import java.util.Map;

import cn._51app.stats.domain.AdeviceDay;

public interface LogAndroidDao {

	/**
	 * tengh 下午6:18:52
	 * TODO 查询所有安卓机型
	 * @return
	 */
	List<Map<String, Object>> getModels();

	/**
	 * tengh 下午6:27:09
	 * TODO 保存设备型号
	 * @param model
	 * @param model2
	 * @return
	 */
	int saveDeviceModel(String model, String model2);

	/**
	 * tengh 下午6:35:01
	 * TODO 查询安卓应用的所有版本
	 * @return
	 */
	List<Map<String, Object>> getAndroidVersions();

	/**
	 * tengh 下午7:13:18
	 * TODO 保存android版本
	 * @param android
	 * @return
	 */
	int saveAndroidVersion(String android);

	/**
	 * tengh 下午7:19:27
	 * TODO 查询所有应用的版本
	 * @param type
	 * @return
	 */
	List<Map<String, Object>> getVersions(int type);

	/**
	 * tengh 下午7:30:44
	 * TODO 保存应用版本号
	 * @param version
	 * @param type
	 * @return
	 */
	int saveVersion(String version, int type);

	/**
	 * tengh 下午7:46:55
	 * TODO 查询设备是否存在
	 * @param adeviceDay
	 * @return
	 */
	Map<String, Object> getDevice(AdeviceDay adeviceDay);

	/**
	 * tengh 下午7:49:54
	 * TODO 更新设备信息
	 * @param adeviceDay
	 */
	void updateDevice(AdeviceDay adeviceDay);

	/**
	 * tengh 下午7:56:14
	 * TODO 修改设备存留
 	 * @param adeviceDay
	 */
	void updateDeviceRetention(AdeviceDay adeviceDay);

	/**
	 * tengh 下午8:01:28
	 * TODO 保存设备数据统计
	 * @param adeviceDay
	 */
	void saveOrUpdateDeviceDay(AdeviceDay adeviceDay);

	/**
	 * tengh 下午8:07:07
	 * TODO 保存设备信息
	 * @param adeviceDay
	 */
	int saveDevice(AdeviceDay adeviceDay);

}

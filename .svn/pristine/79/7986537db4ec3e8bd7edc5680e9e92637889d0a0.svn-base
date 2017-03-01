package cn._51app.stats.dao;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import cn._51app.stats.base.BaseDao;
import cn._51app.stats.domain.AdeviceDay;

public class LogAndroidDaoImpl extends BaseDao implements LogAndroidDao {
	private JdbcTemplate statsJdbc;

	public JdbcTemplate getStatsJdbc() {
		return statsJdbc;
	}

	public void setStatsJdbc(JdbcTemplate statsJdbc) {
		this.statsJdbc = statsJdbc;
	}

	@Override
	public List<Map<String, Object>> getModels() {
		String sql = "SELECT id,model from a_device_model";
		return this.statsJdbc.queryForList(sql);
	}

	@Override
	public int saveDeviceModel(String mobile, String model) {
		String sql = "INSERT INTO a_device_model(mobile, model) VALUES (?, ?)";
		return this.saveRetKey(statsJdbc, sql, new Object[] { mobile, model },
				new int[] { Types.VARCHAR, Types.VARCHAR });
	}

	@Override
	public List<Map<String, Object>> getAndroidVersions() {
		String sql = "SELECT id, code FROM a_android_version LIMIT 1000";
		return this.statsJdbc.queryForList(sql);
	}
	
	@Override
	public int saveAndroidVersion(String code) {
		String sql = "INSERT INTO a_android_version(code) VALUES (?)";
		return this.saveRetKey(statsJdbc, sql, new Object[] { code },
				new int[] { Types.INTEGER });
	}
	
	@Override
	public List<Map<String, Object>> getVersions(int type) {
		String sql = "SELECT id, number FROM a_version WHERE type = ? LIMIT 1000";
		return this.statsJdbc.queryForList(sql, type);
	}

	@Override
	public int saveVersion(String number, int type) {
		String sql = "INSERT INTO a_version(number, type) VALUES (?, ?)";
		return this.saveRetKey(statsJdbc, sql, new Object[] { number, type },
				new int[] { Types.VARCHAR, Types.INTEGER });
	}
	
	@Override
	public Map<String, Object> getDevice(AdeviceDay adeviceDay) {
		String sql = "SELECT id, imei, cid, model, android, version, create_time AS createTime, update_time AS updateTime, type FROM a_device WHERE imei = ? AND type = ?";
		return this.queryForMap(statsJdbc, sql, adeviceDay.getImei(),
				adeviceDay.getType());
	}
	
	@Override
	public void updateDevice(AdeviceDay adeviceDay) {
		String sql = "UPDATE a_device SET cid = ?, model = ?, android = ?, version = ?, update_time = NOW() WHERE id = ?";
		this.update(
				statsJdbc,
				sql,
				new Object[] { adeviceDay.getCid(), adeviceDay.getModel(),
						adeviceDay.getAndroid(), adeviceDay.getVersion(), adeviceDay.getId() });
		
	}
	
	@Override
	public void updateDeviceRetention(AdeviceDay adeviceDay) {
		String sql = "UPDATE a_device_day SET 1day_retention = 1day_retention + ?, 2day_retention = 2day_retention + ?, 3day_retention = 3day_retention + ?, 7day_retention = 7day_retention + ?, 14day_retention = 14day_retention + ?, 30day_retention = 30day_retention + ? WHERE cid = ? AND model = ? AND android = ? AND version = ? AND create_day = ? AND type = ?";
		this.update(
				statsJdbc,
				sql,
				new Object[] { adeviceDay.get_1dayRetention(),
						adeviceDay.get_2dayRetention(),
						adeviceDay.get_3dayRetention(),
						adeviceDay.get_7dayRetention(),
						adeviceDay.get_14dayRetention(),
						adeviceDay.get_30dayRetention(), adeviceDay.getCid(),
						adeviceDay.getModel(), adeviceDay.getAndroid(),
						adeviceDay.getVersion(), adeviceDay.getCreateDay(),
						adeviceDay.getType() });
	}
	
	@Override
	public void saveOrUpdateDeviceDay(AdeviceDay adeviceDay) {
		String sql = "INSERT INTO a_device_day(cid, model, android, version, create_day, type, new, active) VALUES (?, ?, ?, ?, ?, ?, ?, ?)  ON DUPLICATE KEY UPDATE new = new + ?, active = active + ?";
		this.update(
				statsJdbc,
				sql,
				new Object[] { adeviceDay.getCid(), adeviceDay.getModel(),
						adeviceDay.getAndroid(), adeviceDay.getVersion(),
						adeviceDay.getCreateDay(), adeviceDay.getType(),
						adeviceDay.get_new(), adeviceDay.getActive(),
						adeviceDay.get_new(), adeviceDay.getActive() });
	}
	
	@Override
	public int saveDevice(AdeviceDay adeviceDay) {
		String sql = "INSERT INTO a_device(imei, cid, model, android, version, create_time, update_time, type) VALUES (?, ?, ?, ?, ?, NOW(), NOW(), ?)";
		return this.saveRetKey(
				statsJdbc,
				sql,
				new Object[] { adeviceDay.getImei(), adeviceDay.getCid(),
						adeviceDay.getModel(), adeviceDay.getAndroid(),
						adeviceDay.getVersion(), adeviceDay.getType() }, new int[] {
						Types.VARCHAR, Types.INTEGER, Types.INTEGER,
						Types.INTEGER, Types.INTEGER, Types.INTEGER });
	}
}
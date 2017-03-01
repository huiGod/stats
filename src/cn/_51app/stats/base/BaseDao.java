package cn._51app.stats.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public abstract class BaseDao {

	protected int saveRetKey(final JdbcTemplate jdbcTemplate, final String sql, final Object[] params,
			final int[] types) {
		int lastNum = -1;
		KeyHolder keyHolder = new GeneratedKeyHolder(); // 创建一个主健拥有者
		PreparedStatementCreator p = new PreparedStatementCreator() {			
			public PreparedStatement createPreparedStatement(Connection conn) {
				try {
					PreparedStatement ps = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
					if (params != null && types != null
							&& params.length == types.length) {
						int len = params.length;
						for (int i = 0; i < len; i++) {
							ps.setObject((i + 1), params[i]);
						}
					}
					return ps;
				} catch (SQLException e) {
					e.printStackTrace();
				};
				return null;
			}
		};
		jdbcTemplate.update(p, keyHolder);
		lastNum = keyHolder.getKey().intValue();
		return lastNum;
	}
	
	protected int update(final JdbcTemplate jdbcTemplate, final String sql, final Object... values) {
		int recordNum = 0;
		if (values != null && values.length > 0) {
			recordNum = jdbcTemplate.update(sql, values);
		} else {
			recordNum = jdbcTemplate.update(sql);
		}
		return recordNum;
	}
	
	@SuppressWarnings("deprecation")
	protected int getForInt(final JdbcTemplate jdbcTemplate, final String sql, final Object... values) {
		int recondNum = 0;
		try {
			if (values != null && values.length > 0) {
				recondNum = jdbcTemplate.queryForInt(sql, values);
			} else {
				recondNum = jdbcTemplate.queryForInt(sql);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return recondNum;
	}
	
	/**
	 * 执行查询，返回MAP
	 * 
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            参数数组
	 * @return
	 */
	protected Map<String, Object> queryForMap(final JdbcTemplate jdbcTemplate,
			String sql, Object... params) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, params);
		return list == null || list.size() < 1 ? null : list.get(0);
	}

}

package com.algo.webshop.server.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.algo.webshop.common.domain.UserSystem;

public class UserSystemRowMapper implements RowMapper<UserSystem>{

	@Override
	public UserSystem mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserSystem userSystem = new UserSystem();
	      userSystem.setUsername(rs.getString("username"));
	      userSystem.setPassword(rs.getString("password"));
	      userSystem.setAuthorities(rs.getString("authority"));
	      return userSystem;
	}

}

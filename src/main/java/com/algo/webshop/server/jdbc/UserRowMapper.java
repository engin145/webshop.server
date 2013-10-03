package com.algo.webshop.server.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.algo.webshop.common.domain.User;

public class UserRowMapper implements RowMapper<User> {
	   public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	      User user = new User();
	      user.setId(rs.getInt("id"));
	      user.setName(rs.getString("name"));
	      user.setEmail(rs.getString("email"));
	      user.setPhone(rs.getString("phone"));
	      user.setLogin(rs.getString("login"));
	      user.setPass(rs.getString("pass"));
	      return user;
	   }
	   
	    
	}

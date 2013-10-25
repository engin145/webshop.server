package com.algo.webshop.server.services;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algo.webshop.common.domain.UserSystem;
import com.algo.webshop.common.domainimpl.IUserSystem;
import com.algo.webshop.server.jdbc.UserSystemRowMapper;

@Service("userSystemService")
public class UserSystemService extends AbstractService implements IUserSystem{

	@Override
	public UserSystem getUserSystemByName(String username) {
		String SQL = "select * from users_system where username = ?";
		UserSystem userSystem;
		try {
			userSystem = jdbcTemplate.queryForObject(SQL, new Object[] { username }, new UserSystemRowMapper());
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
		
		return userSystem;
	}

}

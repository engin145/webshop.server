package com.algo.webshop.server.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.algo.webshop.common.domain.Unit;

public class UnitRowMapper implements RowMapper<Unit> {

	@Override
	public Unit mapRow(ResultSet rs, int rowNum) throws SQLException {
		Unit unit = new Unit(rs.getInt("id"), rs.getString("name"));
		return unit;
	}

}

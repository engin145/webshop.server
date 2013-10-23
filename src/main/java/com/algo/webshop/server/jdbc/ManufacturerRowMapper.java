package com.algo.webshop.server.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.algo.webshop.common.domain.Manufacturer;


public class ManufacturerRowMapper implements RowMapper<Manufacturer> {

	@Override
	public Manufacturer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Manufacturer manufacturer = new Manufacturer(rs.getInt("id"), rs.getString("name"));
		return manufacturer;
	}
}

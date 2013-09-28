package com.algo.webshop.server.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.algo.webshop.common.domain.Good;

public class GoodRowMapper implements RowMapper<Good> {

	@Override
	public Good mapRow(ResultSet rs, int rowNum) throws SQLException {
		Good good = new Good();
		good.setAmount(rs.getFloat("amount"));
		good.setCategory_id(rs.getInt("category_id"));
		good.setDescription(rs.getString("description"));
		good.setId(rs.getInt("id"));
		good.setManufacturers_id(rs.getInt("manufacturers_id"));
		good.setName(rs.getString("name"));
		good.setUnits_id(rs.getInt("units_id"));
		return good;
	}

}

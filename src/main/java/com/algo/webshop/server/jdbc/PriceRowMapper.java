package com.algo.webshop.server.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.algo.webshop.common.domain.Price;

public class PriceRowMapper  implements RowMapper<Price>{

	@Override
	public Price mapRow(ResultSet rs, int rowNum) throws SQLException {
		Price price = new Price();
		price.setId(rs.getInt("id"));
		price.setGoodId(rs.getInt("goods_id"));
		price.setValue(rs.getFloat("value"));
		price.setDate(rs.getDate("date"));
		return price;
	}
}

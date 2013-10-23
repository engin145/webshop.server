package com.algo.webshop.server.jdbc;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.jdbc.core.RowMapper;

import com.algo.webshop.common.domain.Price;

public class PriceRowMapper  implements RowMapper<Price>{

	@Override
	public Price mapRow(ResultSet rs, int rowNum) throws SQLException {
		Price price = new Price();
		price.setId(rs.getInt("id"));
		price.setGoodId(rs.getInt("goods_id"));
		price.setValue(rs.getFloat("value"));
		Date date = rs.getDate ("date");
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		price.setCalendar(calendar);
		return price;
	}
}

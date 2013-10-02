package com.algo.webshop.server.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.algo.webshop.common.domain.Price;
import com.algo.webshop.common.domainimpl.IPrice;
import com.algo.webshop.server.jdbc.PriceRowMapper;

@Service("priceService")
public class PriceService implements IPrice{
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Price> getAllPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Price> getMaxDateAllPrice() {
		String SQL = "SELECT goods_id, value FROM prices WHERE (goods_id, date) IN (SELECT goods_id, MAX(date) FROM prices GROUP BY goods_id)";
		List<Price> priceList = jdbcTemplate.query(SQL ,new PriceRowMapper());
		return priceList;
	}

	@Override
	public Price getPrice(Integer goodId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Price> getActualDataAllPrice(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Price getActualDatePrice(Date date) {
		// TODO Auto-generated method stub
		return null;
	}
}

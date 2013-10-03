package com.algo.webshop.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.algo.webshop.common.domain.Good;
import com.algo.webshop.common.domainimpl.IGood;
import com.algo.webshop.server.jdbc.GoodRowMapper;

@Service("goodService")
public class GoodService implements IGood {

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Good getGood(int id) {
		Good good = jdbcTemplate.queryForObject("select * from goods where id=?", new Object[] { id },
				new GoodRowMapper());
		return good;
		
	}

	@Override
	public List<Good> getGoods(int category_id) {
		return jdbcTemplate.query("select * from goods where category_id=?", new Object[] { category_id }, new GoodRowMapper());
	}

	@Override
	public void addGood(Good good) {
		String SQL = "insert into goods (name,description,category_id,manufacturers_id,amount,units_id) values (?,?,?,?,?,?)";
		jdbcTemplate.update(SQL, good.getName(), good.getDescription(), good.getCategory_id(), good.getManufacturers_id(), good.getAmount(), good.getUnits_id());
	}

	@Override
	public void updateGood(Good good) {
		String SQL = "update goods set name=?, description=?, category_id=?, manufacturers_id=?, amount=?, units_id=? where id=?";
		jdbcTemplate.update(SQL, good.getName(), good.getDescription(), good.getCategory_id(), good.getManufacturers_id(), good.getAmount(), good.getUnits_id(), good.getId());
	}

	@Override
	public void removeGood(int id) {
		String SQL = "delete from goods where id = ?";
		jdbcTemplate.update(SQL, id);
	}

	@Override
	public String getLongDescription(int good_id) {
		String SQL = "select name from long_descriptions where goods_id=?";
		return jdbcTemplate.queryForObject(SQL, new Object[]{good_id}, String.class);
	}
	

}

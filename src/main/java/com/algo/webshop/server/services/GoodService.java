package com.algo.webshop.server.services;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algo.webshop.common.domain.Good;
import com.algo.webshop.common.domain.GoodsList;
import com.algo.webshop.common.domain.Position;
import com.algo.webshop.common.domainimpl.IGood;
import com.algo.webshop.server.jdbc.GoodRowMapper;

@Service("goodService")
public class GoodService extends AbstractService implements IGood {

	@Override
	public Good getGood(int id) {
		Good good;
		try {
			good = jdbcTemplate.queryForObject(
					"select * from goods where id=?", new Object[] { id },
					new GoodRowMapper());
		} catch (EmptyResultDataAccessException em) {
			good = null;
		}
		return good;

	}

	@Override
	public List<Good> getGoods(int category_id) {
		return jdbcTemplate.query("select * from goods where category_id=?",
				new Object[] { category_id }, new GoodRowMapper());
	}

	@Override
	public int addGood(Good good) {
		String SQL = "insert into goods (name,description,category_id,manufacturers_id,amount,units_id) values (?,?,?,?,?,?)";
		jdbcTemplate.update(SQL, good.getName(), good.getDescription(),
				good.getCategory_id(), good.getManufacturers_id(),
				good.getAmount(), good.getUnits_id());

		good = jdbcTemplate
				.queryForObject(
						"select * from goods where (name) = (?) and (description) = (?) and (category_id) = (?) and (manufacturers_id) = (?) and (units_id) = (?)",
						new Object[] { good.getName(), good.getDescription(),
								good.getCategory_id(),
								good.getManufacturers_id(), good.getUnits_id() },
						new GoodRowMapper());
		return good.getId();
	}

	@Override
	public void updateGood(Good good) {
		String SQL = "update goods set name=?, description=?, category_id=?, manufacturers_id=?, amount=?, units_id=? where id=?";
		jdbcTemplate.update(SQL, good.getName(), good.getDescription(),
				good.getCategory_id(), good.getManufacturers_id(),
				good.getAmount(), good.getUnits_id(), good.getId());
	}

	// Дописать - удалять заодно в таблице price and longDescriptions и т.
	// д.....
	@Override
	public int removeGood(int id) {
		String SQL = "delete from goods where id = ?";
		try {
			jdbcTemplate.update(SQL, id);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	@Override
	public String getLongDescription(int good_id) {
		String SQL = "select name from long_descriptions where goods_id=?";
		try {
			return jdbcTemplate.queryForObject(SQL, new Object[] { good_id },
					String.class);
		} catch (Exception e) {
			return "non description";
		}

	}

	@Override
	public void setLongDescription(int goods_id, String longDescription) {
		if (getLongDescription(goods_id).equals("non description")) {
			String SQL = "insert into long_descriptions (goods_id, name) values (?,?)";
			jdbcTemplate.update(SQL, goods_id, longDescription);
		} else {
			String SQL = "update long_descriptions set name = ? where goods_id = ?";
			jdbcTemplate.update(SQL, longDescription, goods_id);
		}
	}

	@Override
	public String getManufactur(int id) {
		String SQL = "select name from manufacturers where id=?";
		try {
			return jdbcTemplate.queryForObject(SQL, new Object[] { id },
					String.class);
		} catch (Exception e) {
			return "non manufactur";
		}
	}

	@Override
	public List<Good> getGoodsSelect(int category_id, int manufacturer_id,
			int min, int max) {
		String SQL;
		SQL = "select * from goods where category_id=? and manufacturers_id";
		if (manufacturer_id != 0)
			SQL += "=?";
		else
			SQL += ">?";
		SQL += " and amount>=?";
		if (max > -1)
			SQL += " and amount<=?";
		else
			SQL += " and amount>?";
		return jdbcTemplate.query(SQL, new Object[] { category_id,
				manufacturer_id, min, max }, new GoodRowMapper());
	}

	@Override
	public void updateAmount(GoodsList goodList) {
		for (Position good : goodList.getListPosition()) {
			Double goodAmountInStock = jdbcTemplate.queryForObject(
					"select amount from goods where id=?",
					new Object[] { good.getGoods_id() }, Double.class);
			goodAmountInStock = goodAmountInStock - good.getAmount();
			jdbcTemplate.update("update goods set amount=? where id=?",
					goodAmountInStock, good.getGoods_id());
		}
	}

}

package com.algo.webshop.server.services;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.algo.webshop.common.domain.Price;
import com.algo.webshop.common.domainimpl.IPrice;
import com.algo.webshop.server.jdbc.PriceRowMapper;

@Service("priceService")
public class PriceService extends AbstractService implements IPrice {

	@Override
	public float addPrice(Price price) {
		String SQL = "insert into prices (value, date, goods_id) values (?,?,?)";
		jdbcTemplate.update(SQL, price.getValue(), price.getCalendar(),
				price.getGoodId());

		SQL = "select * from prices where date = ? and goods_id = ?";
		price = jdbcTemplate.queryForObject(SQL,
				new Object[] { price.getCalendar(), price.getGoodId() },
				new PriceRowMapper());
		return price.getValue();
	}

	@Override
	public float addPriceSum(Price price) {
		int goodId = price.getGoodId();
		Price priceOld = getMaxDatePriceByOneGood(goodId);
		float valueOld = priceOld.getValue();
		float value = price.getValue();
		valueOld += value;
		price.setValue(valueOld);
		addPrice(price);
		return price.getValue();
	}

	@Override
	public List<Price> getAllPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Price> getMaxDateAllPrice() {
		String SQL = "SELECT * FROM prices WHERE (goods_id, date) IN (SELECT goods_id, MAX(date) FROM prices GROUP BY goods_id)";
		List<Price> priceList = jdbcTemplate.query(SQL, new PriceRowMapper());
		return priceList;
	}

	@Override
	public Price getMaxDatePriceByOneGood(int goodId) {
		String SQL = "SELECT * FROM prices WHERE (goods_id, date) IN (SELECT goods_id, MAX(date) FROM prices GROUP BY goods_id) AND goods_id=?";
		Price price = jdbcTemplate.queryForObject(SQL, new Object[] { goodId },
				new PriceRowMapper());
		return price;
	}

	@Override
	public List<Price> getActualDataAllPrice(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Price> getMaxDatePriceByOneCategory(int categoryId) {
		String SQL = "SELECT * FROM (select * from prices where goods_id in (select id from goods where category_id=?)) as t1 WHERE (t1.goods_id, t1.date) IN (SELECT goods_id, MAX(date) FROM prices GROUP BY goods_id)";
		List<Price> priceList = jdbcTemplate.query(SQL,
				new Object[] { categoryId }, new PriceRowMapper());
		return priceList;
	}

	@Override
	public Price getActualDatePrice(Calendar date, int good_id) {
		return jdbcTemplate
				.queryForObject(
						"select * from prices where goods_id=? and date<=? order by date desc limit 1;",
						new Object[] { good_id, date }, new PriceRowMapper());
	}

}

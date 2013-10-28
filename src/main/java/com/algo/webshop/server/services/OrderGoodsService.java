package com.algo.webshop.server.services;

import java.util.List;

import com.algo.webshop.common.domain.GoodsList;
import com.algo.webshop.common.domain.Position;
import com.algo.webshop.common.domainimpl.IOrderGood;
import com.algo.webshop.server.jdbc.PositionRowMapper;

public class OrderGoodsService extends AbstractService implements IOrderGood {

	@Override
	public void addGoodList(GoodsList goods, int numberOfOrder) {
		String SQL = "insert into order_goods (goods_id, orders_id, amount) values(?,?,?)";
		for (Position good : goods.getListPosition()) {
			jdbcTemplate.update(SQL, good.getGoods_id(), numberOfOrder,
					good.getAmount());
		}

	}

	@Override
	public List<Position> getGoodList(int orderId) {
		return jdbcTemplate.query(
				"select * from order_goods where orders_id=?",
				new Object[] { orderId }, new PositionRowMapper());
	}

}

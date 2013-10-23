package com.algo.webshop.server.services;

import java.util.Calendar;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.algo.webshop.common.domain.Order;
import com.algo.webshop.common.domain.Position;
import com.algo.webshop.common.domainimpl.IOrder;

@Service("orderService")
public class OrderService extends AbstractService implements IOrder {

	@Override
	public Order getOrder(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getOrder(String number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Order> getOrderUser(int users_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Order> getOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Order> getOrders(Calendar dateFrom, Calendar dateTo,
			int status_pay, int status_release, int status_cansel,
			int status_confirm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addOrder(Order order) {
		if (order.getUsers_id() == 0) {
			jdbcTemplate
					.update("insert into orders (number, phone, email, date_order) values (?,?,?,?)",
							order.getNumber(), order.getPhone(),
							order.getEmail(), order.getDate_order());
		} else {
			jdbcTemplate
					.update("insert into orders (number, users_id, date_order) values (?,?,?)",
							order.getNumber(), order.getUsers_id(),
							order.getDate_order());
		}
		String SQL = "insert into order_goods (goods_id, orders_id, amount) values(?,?,?)";
		Integer id = jdbcTemplate.queryForObject(
				"select id from orders where number=?", Integer.class,
				new Object[] { order.getNumber() });
		for (Position good : order.getGoodList().getListPosition()) {
			jdbcTemplate.update(SQL, good.getGoods_id(), id, good.getAmount());
			Double goodAmountInStock = jdbcTemplate.queryForObject("select amount from goods where id=?", new Object[]{good.getGoods_id()}, Double.class);
			goodAmountInStock = goodAmountInStock-good.getAmount();
			jdbcTemplate.update("update goods set amount=? where id=?", goodAmountInStock, good.getGoods_id());
		}

	}

	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getLastOrderNumber() {
		String SQL = "select number from orders where id=(SELECT max(id) FROM orders)";
		return jdbcTemplate.queryForObject(SQL, String.class);
	}

}

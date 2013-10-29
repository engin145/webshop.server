package com.algo.webshop.server.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.jdbc.core.RowMapper;

import com.algo.webshop.common.domain.Order;

public class OrderRowMapper implements RowMapper<Order> {

	@Override
	public Order mapRow(ResultSet rs, int arg1) throws SQLException {
		Order order = new Order();
		order.setId(rs.getInt("id"));
		order.setUsers_id(rs.getInt("users_id"));
		order.setNumber(rs.getString("number"));
		order.setEmail(rs.getString("email"));
		order.setPhone(rs.getString("phone"));
		order.setCansel_status(rs.getInt("cansel_status_id"));
		order.setConfirm_status(rs.getInt("confirm_status_id"));
		Timestamp date = rs.getTimestamp("date_order");
		Calendar calendarOrder = new GregorianCalendar();
		calendarOrder.setTime(date);
		order.setDate_order(calendarOrder);
		date = rs.getTimestamp("date_pay");
		if (date != null) {
			Calendar calendarPay = new GregorianCalendar();
			calendarPay.setTime(date);
			order.setDate_pay(calendarPay);
			
		}
		date = rs.getTimestamp("date_release");
		if (date != null) {
			Calendar calendarRelease = new GregorianCalendar();
			calendarRelease.setTime(date);
			order.setDate_release(calendarRelease);
		}
		return order;
	}

}

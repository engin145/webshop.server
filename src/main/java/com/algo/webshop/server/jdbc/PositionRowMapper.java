package com.algo.webshop.server.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.algo.webshop.common.domain.Position;

public class PositionRowMapper implements RowMapper<Position> {

	@Override
	public Position mapRow(ResultSet rs, int arg1) throws SQLException {
		Position position = new Position();
		position.setAmount(rs.getFloat("amount"));
		position.setGoods_id(rs.getInt("goods_id"));
		return position;
	}

}

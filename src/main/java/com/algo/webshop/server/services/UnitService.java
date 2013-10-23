package com.algo.webshop.server.services;

import java.util.List;
import java.util.Map;

import com.algo.webshop.common.domain.Unit;
import com.algo.webshop.common.domainimpl.IUnit;
import com.algo.webshop.server.jdbc.UnitRowMapper;

public class UnitService extends AbstractService implements IUnit {

	@Override
	public Unit getUnit(int id) {
		String SQL = "select * from units where id = ?";
		Unit unit = jdbcTemplate.queryForObject(SQL, new Object[] { id },
				new UnitRowMapper());
		return unit;
	}

	@Override
	public List<Unit> getUnits() {
		String SQL = "select * from units";
		List<Unit> unitList = jdbcTemplate.query(SQL, new UnitRowMapper());
		return unitList;
	}

	@Override
	public int addUnit(Unit unit) {
		String SQL = "insert into units (name) values (?)";
		jdbcTemplate.update(SQL, new Object[] {unit.getName()});
		SQL = "select * from units where (name) = (?)";
		unit = jdbcTemplate.queryForObject(SQL, new Object[] { unit.getName() },
				new UnitRowMapper());
		return unit.getId();
	}

	@Override
	public void updateUnit(Unit unit) {
		String SQL = "update units set name=? where id=?";
		jdbcTemplate.update(SQL, unit.getName(), unit.getId());
	}

	@Override
	public void delUnit(int id) {
		String SQL = "delete from units where id = ?";
		jdbcTemplate.update(SQL, id);
	}

	@Override
	public Map<String, Object> getMapCategory() {
		String SQL = "select * from units";
		Map<String, Object> m = jdbcTemplate.queryForMap(SQL, new UnitRowMapper());
		return m;
	}
}

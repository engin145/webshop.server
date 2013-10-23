package com.algo.webshop.server.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.algo.webshop.common.domain.Manufacturer;
import com.algo.webshop.common.domainimpl.IManufacturer;
import com.algo.webshop.server.jdbc.ManufacturerRowMapper;

@Service("manufacturerService")
public class ManufacturerService extends AbstractService implements IManufacturer {

	@Override
	public Manufacturer getManufacturer(int id) {
		String SQL = "select * from manufacturers where id = ?";
		Manufacturer manufacturer = jdbcTemplate.queryForObject(SQL, new Object[] { id },
				new ManufacturerRowMapper());
		return manufacturer;
	}

	@Override
	public List<Manufacturer> getManufacturers() {
		String SQL = "select * from manufacturers";
		List<Manufacturer> manufacturerList = jdbcTemplate.query(SQL, new ManufacturerRowMapper());
		return manufacturerList;
	}

	@Override
	public int addManufacturer(Manufacturer manufacturer) {
		String SQL = "insert into manufacturers (name) values (?)";
		jdbcTemplate.update(SQL, new Object[] {manufacturer.getName()});
		SQL = "select * from manufacturers where (name) = (?)";
		manufacturer = jdbcTemplate.queryForObject(SQL, new Object[] { manufacturer.getName() },
				new ManufacturerRowMapper());
		return manufacturer.getId();
	}

	@Override
	public void updateManufacturer(Manufacturer manufacturer) {
		String SQL = "update manufacturers set name=? where id=?";
		jdbcTemplate.update(SQL, manufacturer.getName(), manufacturer.getId());
	}

	@Override
	public void delManufacturer(int id) {
		String SQL = "delete from manufacturers where id = ?";
		jdbcTemplate.update(SQL, id);
	}

	@Override
	public Map<String, Object> getMapManufacturer() {
		String SQL = "select * from manufacturers";
		Map<String, Object> m = jdbcTemplate.queryForMap(SQL, new ManufacturerRowMapper());
		return m;
	}

}

package com.algo.webshop.server.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.algo.webshop.common.domain.Category;
import com.algo.webshop.common.domainimpl.ICategory;
import com.algo.webshop.server.jdbc.CategoryRowMapper;

@Service("categoryService")
public class CategoryService extends AbstractService implements ICategory {
	
	@Override
	public Category getCategory(int id) {
		String SQL = "select * from categorys where id = ?";
		Category category = jdbcTemplate.queryForObject(SQL, new Object[] { id },
				new CategoryRowMapper());
		return category;
	}

	@Override
	public List<Category> getCategorys() {
		String SQL = "select * from categorys";
		List<Category> categoryList = jdbcTemplate.query(SQL, new CategoryRowMapper());
		return categoryList;
	}

	@Override
	public void addCategory(Category category) {
		String SQL = "insert into categorys (name) values (?)";
		jdbcTemplate.update(SQL, new Object[] {category.getName()});
	}

	@Override
	public void updateCategory(Category category) {
		String SQL = "update categorys set name=? where id=?";
		jdbcTemplate.update(SQL, category.getName(), category.getId());
	}

	@Override
	public void delCategory(int id) {
		String SQL = "delete from categorys where id = ?";
		jdbcTemplate.update(SQL, id);
		
	}
	
	public Map<String, Object> getMapCategory(){
		String SQL = "select * from categorys";
		Map<String, Object> m = jdbcTemplate.queryForMap(SQL, new CategoryRowMapper());
		return m;
	}

}

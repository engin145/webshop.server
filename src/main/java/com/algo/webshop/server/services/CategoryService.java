package com.algo.webshop.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.algo.webshop.common.domain.Category;
import com.algo.webshop.common.domainimpl.ICategory;
import com.algo.webshop.server.jdbc.CategoryRowMapper;

@Service("categoryService")
public class CategoryService implements ICategory {
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public Category getCategory(int id) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delCategory(int id) {
		// TODO Auto-generated method stub
		
	}

}

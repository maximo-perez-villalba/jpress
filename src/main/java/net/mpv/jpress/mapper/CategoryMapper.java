package net.mpv.jpress.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.mpv.jpress.model.Category;

public class CategoryMapper implements RowMapper<Category> 
{

	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Category category = new Category();
		category.setId(rs.getLong("id"));
		category.setName(rs.getString("name"));
		category.setDescription(rs.getString("description"));
		category.setCreated(rs.getDate("created"));
		category.setParent_id(rs.getLong("parent_id"));
		return category;
	}

}

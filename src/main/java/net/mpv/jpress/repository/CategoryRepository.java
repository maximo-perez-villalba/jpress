package net.mpv.jpress.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.mpv.jpress.mapper.CategoryMapper;
import net.mpv.jpress.model.Category;

@Repository
public class CategoryRepository extends DBRepository<Category>
{

	@Override
	public Category getById(long id) 
	{
		return this.jdbcTemplate.queryForObject(
				"SELECT * FROM categories WHERE id = ?;",
				new CategoryMapper(),
				new Object[]{id}
			);
	}

	@Override
	public List<Category> getAll(Pageable pageable) 
	{
		return this.jdbcTemplate.query(
				"SELECT * FROM categories;", 
				new CategoryMapper()
			);
	}

	@Override
	protected String queryInsert(Category category) 
	{
		return String.format(
				"INSERT INTO categories (name, description) VALUES ('%s','%s');", 
				category.getName(),
				category.getDescription()
			);
	}

	@Override
	protected String queryUpdate(Category category) 
	{
		return String.format(
				"UPDATE categories SET name='%s', description='%s' WHERE id='%d';", 
				category.getName(),
				category.getDescription(),
				category.getId()
			);
	}

	@Override
	protected String queryDelete(Category category) 
	{
		return String.format("DELETE FROM categories WHERE id='%d';", category.getId());
	}

}

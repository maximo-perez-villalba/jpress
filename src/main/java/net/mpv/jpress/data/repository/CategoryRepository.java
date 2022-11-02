package net.mpv.jpress.data.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.mpv.jpress.data.mapper.CategoryMapper;
import net.mpv.jpress.data.model.Category;

@Repository
public class CategoryRepository extends DBRepository<Category>
{

	@Override
	public Category getById(long id) 
	{
		Category category = null;
		try 
		{
			category = this.jdbcTemplate.queryForObject(
					"SELECT * FROM categories WHERE id = ?;",
					new CategoryMapper(),
					new Object[]{id}
				); 
		}
		catch(Exception exc) 
		{
			this.setLastException(exc);
		}
		
		return category;
	}

	public Category getByName(String name) 
	{
		Category category = null;

		try 
		{
			category = this.jdbcTemplate.queryForObject(
					"SELECT * FROM categories WHERE name = ?;",
					new CategoryMapper(),
					new Object[]{name}
				); 
		}
		catch(Exception exc) 
		{
			this.setLastException(exc);
		}
		
		return category; 
	}

	@Override
	public List<Category> getAll() 
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
				this.sanitize(category.getName()),
				this.sanitize(category.getDescription())
			);
	}

	@Override
	protected String queryUpdate(Category category) 
	{
		return String.format(
				"UPDATE categories SET name='%s', description='%s' WHERE id = %d;", 
				this.sanitize(category.getName()),
				this.sanitize(category.getDescription()),
				category.getId()
			);
	}

	@Override
	protected String queryDelete(Category category) 
	{
		return String.format("DELETE FROM categories WHERE id = %d;", category.getId());
	}

	@Override
	protected String queryClean() 
	{
		return String.format("DELETE FROM categories;");	
	}

	@Override
	public boolean deleteById(long id) 
	{
		return this.execute(
				String.format(
						"DELETE FROM categories WHERE id = %d;", 
						id
					)
				);
	}

}

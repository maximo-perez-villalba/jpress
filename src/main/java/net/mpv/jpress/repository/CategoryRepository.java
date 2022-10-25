package net.mpv.jpress.repository;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.mpv.jpress.model.Category;

@Repository
public class CategoryRepository extends DBRepository<Category>
{

	@Override
	public List<Category> getAll(Pageable pageable) 
	{
		List<Category> list = new ArrayList<Category>() ;
		return list;
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

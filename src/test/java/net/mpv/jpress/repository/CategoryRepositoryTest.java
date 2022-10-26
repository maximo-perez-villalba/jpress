package net.mpv.jpress.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import net.mpv.jpress.components.TestDatabaseConfiguration;
import net.mpv.jpress.model.Category;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
class CategoryRepositoryTest 
{

	@Autowired
	private CategoryRepository repository;

	@Test
	void testFindById() 
	{
		this.repository.clean();
		
		Category category = new Category();
		category.setName("TopXYZ");
		category.setDescription("Superior category");
		category.setParent_id(0);
		
		boolean response = this.repository.save(category);
		if(response)
		{
			category = this.repository.getByName("TopXYZ");
			long idExpected = category.getId(); 
			category = this.repository.getById(idExpected);
			
			Assert.assertEquals(idExpected, category.getId());
		}
		else
		{
			Assert.assertTrue(false);
		}
		this.repository.clean();
	}

	@Test
	void testFindByName() 
	{
		this.repository.clean();
		
		Category category = new Category();
		category.setName("Top");
		category.setDescription("Superior category");
		category.setParent_id(0);
		
		boolean response = this.repository.save(category);
		if(response)
		{
			category = this.repository.getByName("Top");
			Assert.assertEquals("Superior category", category.getDescription());
		}
		else
		{
			Assert.assertTrue(false);
		}
		this.repository.clean();
	}

	@Test
	void testGetAll() 
	{
		this.repository.clean();
		
		Category category = new Category();
		category.setName("Top 1");
		category.setDescription("Category 1");
		category.setParent_id(0);
		
		boolean response = this.repository.save(category);
		
		if(response)
		{
			category = new Category();
			category.setName("Top 2");
			category.setDescription("Category 2");
			category.setParent_id(0);
			
			response = this.repository.save(category);
		}
		if(response)
		{
			category = new Category();
			category.setName("Top 3");
			category.setDescription("Category 3");
			category.setParent_id(0);
			
			response = this.repository.save(category);
		}
		if(response)
		{
			List<Category> list = this.repository.getAll();
			Assert.assertEquals(3, list.size());
		}
		else
		{
			Assert.assertTrue(false);
		}
		this.repository.clean();
	}

	@Test
	void testSave() 
	{
		this.repository.clean();
		
		Category category = new Category();
		category.setName("Top");
		category.setDescription("Superior category");
		category.setParent_id(0);
		
		boolean response = this.repository.save(category);
		Assert.assertTrue(response);
		this.repository.clean();
	}

	@Test
	void testUpdate() 
	{
		this.repository.clean();
		
		Category category = new Category();
		category.setName("Top");
		category.setDescription("Superior category");
		category.setParent_id(0);
		
		boolean response = this.repository.save(category);
		if(response)
		{
			category = this.repository.getByName("Top");
			
			category.setName("Top2");
			category.setDescription("Two superior category");
			this.repository.update(category);
			
			category = this.repository.getById(category.getId());
			
			Assert.assertEquals("Top2", category.getName());
			Assert.assertEquals("Two superior category", category.getDescription());
		}
		else
		{
			Assert.assertTrue(false);
		}
		this.repository.clean();
	}

	@Test
	void testDelete() 
	{
		this.repository.clean();
		
		Category category = new Category();
		category.setName("Top to Delete");
		category.setDescription("Superior category");
		category.setParent_id(0);
		
		boolean response = this.repository.save(category);
		if(response)
		{
			category = this.repository.getByName("Top to Delete");
			Assert.assertTrue(this.repository.delete(category));
		}
		else
		{
			Assert.assertTrue(false);
		}
	}

}

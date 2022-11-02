package net.mpv.jpress.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import net.mpv.jpress.components.TestDatabaseConfiguration;
import net.mpv.jpress.data.model.Category;
import net.mpv.jpress.data.repository.CategoryRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
class CategoryRepositoryTest 
{

	@Autowired
	private CategoryRepository repository;
	
	@AfterEach
	protected void tearDown() throws Exception
	{
		this.repository.clean();
	}

	@Test
	void testFindById() 
	{
		Category category = new Category();
		category.setName("TopXYZ");
		category.setDescription("Superior category");
		
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
	}

	@Test
	void testFindByName() 
	{
		Category category = new Category();
		category.setName("Top");
		category.setDescription("Superior category");
		
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
	}

	@Test
	void testGetAll() 
	{
		Category category = new Category();
		category.setName("Top 1");
		category.setDescription("Category 1");
		
		boolean response = this.repository.save(category);
		
		if(response)
		{
			category = new Category();
			category.setName("Top 2");
			category.setDescription("Category 2");
			
			response = this.repository.save(category);
		}
		if(response)
		{
			category = new Category();
			category.setName("Top 3");
			category.setDescription("Category 3");
			
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
	}

	@Test
	void testSave() 
	{
		Category category = new Category();
		category.setName("Top");
		category.setDescription("Superior category");
		
		boolean response = this.repository.save(category);
		Assert.assertTrue(response);
	}

	@Test
	void testUpdate() 
	{
		Category category = new Category();
		category.setName("Top");
		category.setDescription("Superior category");
		
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
	}

	@Test
	void testDelete() 
	{
		Category category = new Category();
		category.setName("Top to Delete");
		category.setDescription("Superior category");
		
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

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
import net.mpv.jpress.model.Group;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
class GroupRepositoryTest 
{

	@Autowired
	private GroupRepository repository;

	@AfterEach
    void tearDown() 
	{
		this.repository.clean();
    }
	
	@Test
	void testFindByName() 
	{
		Group group = new Group();
		group.setName("A Group of users");
		
		boolean response = this.repository.save(group);
		if(response)
		{
			group = this.repository.getByName("A Group of users");
			Assert.assertEquals("A Group of users", group.getName());
		}
		else
		{
			Assert.assertTrue(false);
		}	
	}
	
	@Test
	void testFindById() 
	{
		Group group = new Group();
		group.setName("A Group of users");
		
		boolean response = this.repository.save(group);
		if(response)
		{
			group = this.repository.getByName("A Group of users");
			long expectedId = group.getId();
			group = this.repository.getById(expectedId);
			Assert.assertEquals(expectedId, group.getId());
		}
		else
		{
			Assert.assertTrue(false);
		}	
	}

	@Test
	void testGetAll() 
	{
		Group group = new Group();
		group.setName("A Group of Cherries");
		
		boolean response = this.repository.save(group);
		if(response)
		{
			group = new Group();
			group.setName("A Group of Tomatos");
			response = this.repository.save(group);
		}
		if(response)
		{
			group = new Group();
			group.setName("A Group of Apples");
			response = this.repository.save(group);
		}
		
		if(response)
		{
			List<Group> list = this.repository.getAll();
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
		Group group = new Group();
		group.setName("A Group of users");
		
		boolean response = this.repository.save(group);
		Assert.assertTrue(response);
	}

	@Test
	void testUpdate() 
	{
		Group group = new Group();
		group.setName("A Group of tests");
		
		boolean response = this.repository.save(group);
		if(response)
		{
			group = this.repository.getByName("A Group of tests");
			group.setName("A Group of TESTS");
			response = this.repository.update(group);
		}		
		
		if(response)
		{
			Assert.assertEquals("A Group of TESTS", group.getName());
		}
		else
		{
			Assert.assertTrue(false);
		}	
	}

	@Test
	void testDelete() 
	{
		Group group = new Group();
		group.setName("A Group of tests");
		
		boolean response = this.repository.save(group);
		if(response)
		{
			group = this.repository.getByName("A Group of tests");
			response = this.repository.delete(group);
		}		
		
		Assert.assertTrue(response);
	}

}

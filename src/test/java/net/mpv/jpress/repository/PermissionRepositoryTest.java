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
import net.mpv.jpress.model.Permission;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
class PermissionRepositoryTest 
{

	@Autowired
	private PermissionRepository repository;
	
	@AfterEach
    void tearDown() 
	{
		this.repository.clean();
    }
	
	@Test
	void testGetById() 
	{
		Permission permission = new Permission();
		permission.setName("Login.show");
		
		boolean response = this.repository.save(permission);
		if(response) 
		{
			permission = this.repository.getByName("Login.show");
			long expectedId = permission.getId();
			permission = this.repository.getById(expectedId);
			Assert.assertEquals(expectedId, permission.getId());
		}
		else
		{
			Assert.assertTrue(false);	
		}
	}
	
	@Test
	void testGetByIdWithInvalidValue() 
	{
		Permission permission = this.repository.getById(-1);
		Assert.assertNull(permission);
	}

	@Test
	void testGetByName() 
	{
		Permission permission = new Permission();
		permission.setName("Login.show");
		
		boolean response = this.repository.save(permission);
		if(response) 
		{
			permission = this.repository.getByName("Login.show");
			Assert.assertEquals("Login.show", permission.getName());
		}
		else
		{
			Assert.assertTrue(false);	
		}
	}

	@Test
	void testGetByNameWithInvalidValue() 
	{
		Permission permission = this.repository.getByName("ABCD EFJH");
		Assert.assertNull(permission);
	}

	@Test
	void testGetAll() 
	{
		Permission permission = new Permission();
		permission.setName("Login.show");
		
		boolean response = this.repository.save(permission);
		if(response) 
		{
			permission = new Permission();
			permission.setName("Login.hide");
			
			response = this.repository.save(permission);
		}
		if(response) 
		{
			permission = new Permission();
			permission.setName("Login.disabled");
			
			response = this.repository.save(permission);
		}

		if(response) 
		{
			List<Permission> list = this.repository.getAll();
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
		Permission permission = new Permission();
		permission.setName("Login.show");
		
		boolean response = this.repository.save(permission);
		Assert.assertTrue(response);
	}

	@Test
	void testUpdate() 
	{
		Permission permission = new Permission();
		permission.setName("Login.show");
		
		boolean response = this.repository.save(permission);
		if(response) 
		{
			permission = this.repository.getByName("Login.show");
			permission.setName("Login.SHOW");
			
			response = this.repository.update(permission);
		}
		
		if(response) 
		{
			permission = this.repository.getByName("Login.SHOW");
			Assert.assertEquals("Login.SHOW", permission.getName());
		}
		else
		{
			Assert.assertTrue(false);	
		}
	}

	@Test
	void testDelete() 
	{
		Permission permission = new Permission();
		permission.setName("Login.show");
		
		boolean response = this.repository.save(permission);
		if(response) 
		{
			permission = this.repository.getByName("Login.show");
			
			response = this.repository.delete(permission);
		}
		
		Assert.assertTrue(response);	
	}

}

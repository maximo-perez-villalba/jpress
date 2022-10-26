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
import net.mpv.jpress.model.User;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
class UserRepositoryTest 
{

	@Autowired
	private UserRepository repository;

	@Autowired
	private GroupRepository groupRepository;	
	
	@AfterEach
    void tearDown() 
	{
		this.repository.clean();
		this.groupRepository.clean();
    }

	@Test
	void testGetById() 
	{
		Group group = this.mockupGroup();
		
		User user = new User();
		user.setEmail("nombre@email.com");
		user.setFirstname("Nombre");
		user.setLastname("Apellido");
		user.setPassword("estoEs1contraseñA");
		user.setGroup_id(group.getId());
		
		boolean response = this.repository.save(user);
		if(response)
		{
			user = this.repository.getByEmail("nombre@email.com");
			long expectedId  = user.getId();
			
			user = this.repository.getById(expectedId);
			Assert.assertEquals(expectedId, user.getId());
		}
		else
		{
			Assert.assertTrue(false);	
		}
	}

	@Test
	void testGetByEmail() 
	{
		Group group = this.mockupGroup();
		
		User user = new User();
		user.setEmail("nombre@email.com");
		user.setFirstname("Nombre");
		user.setLastname("Apellido");
		user.setPassword("estoEs1contraseñA");
		user.setGroup_id(group.getId());
		
		boolean response = this.repository.save(user);
		if(response)
		{
			user = this.repository.getByEmail("nombre@email.com");
			Assert.assertEquals("nombre@email.com", user.getEmail());
		}
		else
		{
			Assert.assertTrue(false);	
		}
	}

	@Test
	void testGetAll() 
	{
		Group group = this.mockupGroup();
		
		User user = new User();
		user.setEmail("nombre@email.com");
		user.setFirstname("Nombre");
		user.setLastname("Apellido");
		user.setPassword("estoEs1contraseñA");
		user.setGroup_id(group.getId());
		
		boolean response = this.repository.save(user);
		if(response)
		{
			user = new User();
			user.setEmail("nombre1@email.com");
			user.setFirstname("Nombre1");
			user.setLastname("Apellido1");
			user.setPassword("estoEs1contraseñA1");
			user.setGroup_id(group.getId());
			response = this.repository.save(user);
		}
		if(response)
		{
			user = new User();
			user.setEmail("nombre2@email.com");
			user.setFirstname("Nombre2");
			user.setLastname("Apellido2");
			user.setPassword("estoEs1contraseñA2");
			user.setGroup_id(group.getId());
			response = this.repository.save(user);
		}
		
		if(response)
		{
			List<User> list = this.repository.getAll();
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
		Group group = this.mockupGroup();
		
		User user = new User();
		user.setEmail("nombre@email.com");
		user.setFirstname("Nombre");
		user.setLastname("Apellido");
		user.setPassword("estoEs1contraseñA");
		user.setGroup_id(group.getId());
		
		boolean response = this.repository.save(user);
		Assert.assertTrue(response);
	}

	@Test
	void testUpdate() 
	{
		Group group = this.mockupGroup();
		
		User user = new User();
		user.setEmail("nombre@email.com");
		user.setFirstname("Nombre");
		user.setLastname("Apellido");
		user.setPassword("estoEs1contraseñA");
		user.setGroup_id(group.getId());
		
		boolean response = this.repository.save(user);
		if(response)
		{
			user = this.repository.getByEmail("nombre@email.com");
			
			user.setEmail("nombre1@email.com");
			user.setFirstname("Nombre1");
			user.setLastname("Apellido1");
			user.setPassword("estoEs1contraseñA1");
			response = this.repository.update(user);
		}
		
		if(response)
		{
			user = this.repository.getByEmail("nombre1@email.com");
			Assert.assertEquals("Nombre1", user.getFirstname());	
		}
		else
		{
			Assert.assertTrue(false);	
		}
	}

	@Test
	void testDelete() 
	{
		Group group = this.mockupGroup();
		
		User user = new User();
		user.setEmail("nombre@email.com");
		user.setFirstname("Nombre");
		user.setLastname("Apellido");
		user.setPassword("estoEs1contraseñA");
		user.setGroup_id(group.getId());
		
		boolean response = this.repository.save(user);
		
		user = this.repository.getByEmail("nombre@email.com");
		response = this.repository.delete(user);
		
		Assert.assertTrue(response);
	}

	private Group mockupGroup() 
	{
		Group group = new Group();
		group.setName("Categoría");
		this.groupRepository.save(group);

		return this.groupRepository.getByName("Categoría");
	}
	
}

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
import net.mpv.jpress.data.model.User;
import net.mpv.jpress.data.model.UserMetaData;
import net.mpv.jpress.data.repository.UserMetaDataRepository;
import net.mpv.jpress.data.repository.UserRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
class UserMetaDataRepositoryTest 
{
	@Autowired
	private UserMetaDataRepository repository;

	@Autowired
	private UserRepository userRepository;
	
	@AfterEach
    void tearDown() 
	{
		this.repository.clean();
		this.userRepository.clean();
    }

	@Test
	void testGetById() 
	{
		User user = this.mockupUser();
		
		UserMetaData metadata = new UserMetaData();
		metadata.setKey("Clave");
		metadata.setValue("Un valor");
		metadata.setUsers_id(user.getId());
		
		boolean response = this.repository.save(metadata);
		if(response)
		{
			metadata = this.repository.getByKey(user.getId(), "Clave");
			long expectedId = metadata.getId();
			metadata = this.repository.getById(expectedId);
			Assert.assertEquals(expectedId, metadata.getId());
		}
		else 
		{
			Assert.assertTrue(false);	
		}
	}

	@Test
	void testGetByKey() 
	{
		User user = this.mockupUser();
		
		UserMetaData metadata = new UserMetaData();
		metadata.setKey("Clave");
		metadata.setValue("Un valor");
		metadata.setUsers_id(user.getId());
		
		boolean response = this.repository.save(metadata);
		if(response)
		{
			metadata = this.repository.getByKey(user.getId(), "Clave");
			Assert.assertEquals("Un valor", metadata.getValue());
		}
		else 
		{
			Assert.assertTrue(false);	
		}
	}

	@Test
	void testGetAll() 
	{
		User user = this.mockupUser();
		
		UserMetaData metadata = new UserMetaData();
		metadata.setKey("Clave");
		metadata.setValue("Un valor");
		metadata.setUsers_id(user.getId());
		
		boolean response = this.repository.save(metadata);
		if(response)
		{
			metadata = new UserMetaData();
			metadata.setKey("Clave1");
			metadata.setValue("Un valor 1");
			metadata.setUsers_id(user.getId());
			
			response = this.repository.save(metadata);
		}
		if(response)
		{
			metadata = new UserMetaData();
			metadata.setKey("Clave1");
			metadata.setValue("Un valor 1");
			metadata.setUsers_id(user.getId());
			
			response = this.repository.save(metadata);
		}
		
		if(response)
		{
			List<UserMetaData> list = this.repository.getAll();
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
		User user = this.mockupUser();
		
		UserMetaData metadata = new UserMetaData();
		metadata.setKey("Clave");
		metadata.setValue("Un valor");
		metadata.setUsers_id(user.getId());
		
		boolean response = this.repository.save(metadata);
		Assert.assertTrue(response);
	}

	@Test
	void testUpdate() 
	{
		User user = this.mockupUser();
		
		UserMetaData metadata = new UserMetaData();
		metadata.setKey("Clave");
		metadata.setValue("Un valor");
		metadata.setUsers_id(user.getId());
		
		boolean response = this.repository.save(metadata);
		if(response)
		{
			metadata = this.repository.getByKey(user.getId(), "Clave");
			metadata.setValue("A value");
			response = this.repository.update(metadata);
		}

		if(response)
		{
			metadata = this.repository.getByKey(user.getId(), "Clave");
			Assert.assertEquals("A value", metadata.getValue());
		}
		else 
		{
			Assert.assertTrue(false);	
		}
	}

	@Test
	void testDelete() 
	{
		User user = this.mockupUser();
		
		UserMetaData metadata = new UserMetaData();
		metadata.setKey("Clave");
		metadata.setValue("Un valor");
		metadata.setUsers_id(user.getId());
		
		boolean response = this.repository.save(metadata);
		if(response)
		{
			metadata = this.repository.getByKey(user.getId(), "Clave");
			response = this.repository.delete(metadata);
		}
		
		Assert.assertTrue(response);	
	}

	private User mockupUser() 
	{
		User user = new User();
		user.setEmail("nombre@email.com");
		user.setFirstname("Nombre");
		user.setLastname("Apellido");
		user.setPassword("estoEs1contraseñA");
		this.userRepository.save(user);
		
		return this.userRepository.getByEmail("nombre@email.com");
	}
	
}

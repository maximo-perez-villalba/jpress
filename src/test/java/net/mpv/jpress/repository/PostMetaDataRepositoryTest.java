package net.mpv.jpress.repository;

import java.util.List;
import java.util.Objects;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import net.mpv.jpress.components.TestDatabaseConfiguration;
import net.mpv.jpress.data.model.Category;
import net.mpv.jpress.data.model.Post;
import net.mpv.jpress.data.model.PostMetaData;
import net.mpv.jpress.data.model.User;
import net.mpv.jpress.data.repository.CategoryRepository;
import net.mpv.jpress.data.repository.PostMetaDataRepository;
import net.mpv.jpress.data.repository.PostRepository;
import net.mpv.jpress.data.repository.UserRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
@ActiveProfiles("test")
class PostMetaDataRepositoryTest 
{

	@Autowired
	private PostMetaDataRepository repository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@BeforeEach
	void init() 
	{
		boolean value = false;
		this.repository.setVerbose(value);
		this.categoryRepository.setVerbose(value);
		this.userRepository.setVerbose(value);
		this.postRepository.setVerbose(value);
	}
	
	@AfterEach
    void tearDown() 
	{
		this.repository.clean();
		this.postRepository.clean();
		this.categoryRepository.clean();
		this.userRepository.clean();
    }
    
	@Test
	void testGetById() 
	{
		Post post = this.mockupPost();
		
		PostMetaData metadata = new PostMetaData();
		metadata.setKey("Una Clave");
		metadata.setValue("Esto sería un valor");
		metadata.setPost_id(post.getId());
		
		boolean response = this.repository.save(metadata);
		
		if(response) 
		{
			metadata = this.repository.getByKey("Una Clave", post.getId());
			metadata = this.repository.getById(metadata.getId());
			Assert.assertNotNull(metadata);
		}
		else 
		{
			Assert.assertTrue(false);
		}
	}
    
	@Test
	void testGetByIdWithInvalidValue() 
	{
		PostMetaData metadata = this.repository.getById(-1);
		Assert.assertNull(metadata);
	}

	@Test
	void testGetByKey() 
	{
		Post post = this.mockupPost();
		
		PostMetaData metadata = new PostMetaData();
		metadata.setKey("Una Clave");
		metadata.setValue("Esto sería un valor");
		metadata.setPost_id(post.getId());
		
		boolean response = this.repository.save(metadata);
		
		if(response) 
		{
			metadata = this.repository.getByKey("Una Clave", post.getId());
			Assert.assertNotNull(metadata);
		}
		else 
		{
			Assert.assertTrue(false);
		}
	}

	@Test
	void testGetByKeyWithInvalidValue() 
	{
		Post post = this.mockupPost();
		PostMetaData metadata = this.repository.getByKey("Not exit", post.getId());
		Assert.assertNull(metadata);
	}

	@Test
	void testGetAll() 
	{
		Post post = this.mockupPost();
		
		PostMetaData metadata = new PostMetaData();
		metadata.setKey("Una Clave");
		metadata.setValue("Esto sería un valor");
		metadata.setPost_id(post.getId());
		
		boolean response = this.repository.save(metadata);
		
		if(response) 
		{
			metadata = new PostMetaData();
			metadata.setKey("Una Clave1");
			metadata.setValue("Esto sería un valor1");
			metadata.setPost_id(post.getId());
			
			response = this.repository.save(metadata);
		}
		if(response) 
		{
			metadata = new PostMetaData();
			metadata.setKey("Una Clave1");
			metadata.setValue("Esto sería un valor1");
			metadata.setPost_id(post.getId());
			
			response = this.repository.save(metadata);
		}

		if(response) 
		{
			List<PostMetaData> list = this.repository.getAll();
			Assert.assertEquals(3, list.size());;
		}
		else 
		{
			Assert.assertTrue(false);
		}
	}

	@Test
	void testSave() 
	{
		Post post = this.mockupPost();
		
		PostMetaData metadata = new PostMetaData();
		metadata.setKey("Una Clave");
		metadata.setValue("Esto sería un valor");
		metadata.setPost_id(post.getId());
		
		boolean response = this.repository.save(metadata);
		
		Assert.assertTrue(response);
	}

	@Test
	void testUpdate() 
	{
		Post post = this.mockupPost();
		
		PostMetaData metadata = new PostMetaData();
		metadata.setKey("Una Clave");
		metadata.setValue("Esto sería un valor");
		metadata.setPost_id(post.getId());
		
		boolean response = this.repository.save(metadata);
		
		if(response) 
		{
			metadata = this.repository.getByKey("Una Clave", post.getId());
			metadata.setValue("This is a value");
			this.repository.update(metadata);
			
			metadata = this.repository.getById(metadata.getId());
			Assert.assertEquals("This is a value", metadata.getValue());
		}
		else 
		{
			Assert.assertTrue(false);
		}
	}

	@Test
	void testDelete() 
	{
		Post post = this.mockupPost();
		
		PostMetaData metadata = new PostMetaData();
		metadata.setKey("Una Clave");
		metadata.setValue("Esto sería un valor");
		metadata.setPost_id(post.getId());
		
		boolean response = this.repository.save(metadata);
		
		if(response) 
		{
			metadata = this.repository.getByKey("Una Clave", post.getId());
			response = this.repository.delete(metadata);
		}
		
		Assert.assertTrue(response);
	}

	private Post mockupPost() 
	{
		Post post = this.postRepository.getBySlug("este-es-un-slug");
		if(Objects.isNull(post)) 
		{
			User user = this.mockupUser();
			Category category = this.mockupCategory();
			
			post = new Post();
			post.setSlug("este-es-un-slug");
			post.setTitle("Esto es un título");
			post.setExcerpt("Lorem ipsum...");
			post.setFeatured_image_url("/path/to/an/image");
			post.setType("Esto discrimina los publicaciones");
			post.setUser_id(user.getId());
			post.setCategory_id(category.getId());
			this.postRepository.save(post);
			
			post = this.postRepository.getBySlug("este-es-un-slug");
		}
		return post;
	}
	
	private Category mockupCategory() 
	{
		Category category = this.categoryRepository.getByName("Categoria ZZZ");
		if(Objects.isNull(category)) 
		{
			category = new Category();
			category.setName("Categoria ZZZ");
			category.setDescription("Lorem ipsum...");
			this.categoryRepository.save(category);
			category = this.categoryRepository.getByName("Categoria ZZZ");
		}
		return category;
	}

	private User mockupUser() 
	{
		User user = this.userRepository.getByEmail("nombre@email.com");
		if(Objects.isNull(user)) 
		{
			user = new User();
			user.setEmail("nombre@email.com");
			user.setFirstname("Nombre");
			user.setLastname("Apellido");
			user.setPassword("estoES1Contraseña");
			this.userRepository.save(user);
			user = this.userRepository.getByEmail("nombre@email.com");
		}
		return user;
	}

}

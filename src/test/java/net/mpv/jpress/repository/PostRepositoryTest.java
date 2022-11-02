package net.mpv.jpress.repository;

import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
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
import net.mpv.jpress.data.model.User;
import net.mpv.jpress.data.repository.CategoryRepository;
import net.mpv.jpress.data.repository.PostRepository;
import net.mpv.jpress.data.repository.UserRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
@ActiveProfiles("test")
class PostRepositoryTest 
{
	@Autowired
	private PostRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;
	
	@AfterEach
    void tearDown() 
	{
		this.repository.clean();
		this.categoryRepository.clean();
		this.userRepository.clean();
    }

	@Test
	void testGetById() 
	{
		User user = this.mockupUser();
		Category category = this.mockupCategory();
		
		Post post = new Post();
		post.setSlug("este-es-un-slug");
		post.setTitle("Esto es un título");
		post.setExcerpt("Lorem ipsum...");
		post.setFeatured_image_url("/path/to/an/image");
		post.setType("Esto discrimina los publicaciones");
		post.setUser_id(user.getId());
		post.setCategory_id(category.getId());
		boolean response = this.repository.save(post);
		
		if(response) 
		{
			post = this.repository.getBySlug("este-es-un-slug");
			long expectedId = post.getId();
			post = this.repository.getById(expectedId);
			Assert.assertEquals(expectedId, post.getId());
		}
		else 
		{
			Assert.assertTrue(false);
		}
	}

	@Test
	void testGetByIdWithInvalidValue() 
	{
		Post post = this.repository.getById(-1);
		Assert.assertNull(post);;
	}

	@Test
	void testGetBySlug() 
	{
		User user = this.mockupUser();
		Category category = this.mockupCategory();
		
		Post post = new Post();
		post.setSlug("este-es-un-slug");
		post.setTitle("Esto es un título");
		post.setExcerpt("Lorem ipsum...");
		post.setFeatured_image_url("/path/to/an/image");
		post.setType("Esto discrimina los publicaciones");
		post.setUser_id(user.getId());
		post.setCategory_id(category.getId());
		boolean response = this.repository.save(post);
		
		if(response) 
		{
			post = this.repository.getBySlug("este-es-un-slug");
			Assert.assertEquals("este-es-un-slug", post.getSlug());
		}
		else 
		{
			Assert.assertTrue(false);
		}
	}

	@Test
	void testGetBySlugWithInvalidValue() 
	{
		Post post = this.repository.getBySlug("GttY56$4");
		assertNull(post);
	}
	
	@Test
	void testGetAll() 
	{
		User user = this.mockupUser();
		Category category = this.mockupCategory();
		
		Post post = new Post();
		post.setSlug("este-es-un-slug");
		post.setTitle("Esto es un título");
		post.setExcerpt("Lorem ipsum...");
		post.setFeatured_image_url("/path/to/an/image");
		post.setType("Esto discrimina los publicaciones");
		post.setUser_id(user.getId());
		post.setCategory_id(category.getId());
		boolean response = this.repository.save(post);
		
		if(response) 
		{
			post = new Post();
			post.setSlug("este-es-un-slug1");
			post.setTitle("Esto es un título1");
			post.setExcerpt("Lorem ipsum...1");
			post.setFeatured_image_url("/path/to/an/image1");
			post.setType("Esto discrimina los publicaciones");
			post.setUser_id(user.getId());
			post.setCategory_id(category.getId());
			response = this.repository.save(post);		
		}
		if(response) 
		{
			post = new Post();
			post.setSlug("este-es-un-slug2");
			post.setTitle("Esto es un título2");
			post.setExcerpt("Lorem ipsum...2");
			post.setFeatured_image_url("/path/to/an/image2");
			post.setType("Esto discrimina los publicaciones");
			post.setUser_id(user.getId());
			post.setCategory_id(category.getId());
			response = this.repository.save(post);		
		}
		
		if(response) 
		{
			List<Post> list = this.repository.getAll();
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
		Category category = this.mockupCategory();
		
		Post post = new Post();
		post.setSlug("este-es-un-slug");
		post.setTitle("Esto es un título");
		post.setExcerpt("Lorem ipsum...");
		post.setFeatured_image_url("/path/to/an/image");
		post.setType("Esto discrimina los publicaciones");
		post.setUser_id(user.getId());
		post.setCategory_id(category.getId());
		boolean response = this.repository.save(post);
		
		Assert.assertTrue(response);
	}

	@Test
	void testUpdate() 
	{
		User user = this.mockupUser();
		Category category = this.mockupCategory();
		
		Post post = new Post();
		post.setSlug("este-es-un-slug");
		post.setTitle("Esto es un título");
		post.setExcerpt("Lorem ipsum...");
		post.setFeatured_image_url("/path/to/an/image");
		post.setType("Esto discrimina los publicaciones");
		post.setUser_id(user.getId());
		post.setCategory_id(category.getId());
		boolean response = this.repository.save(post);
		
		if(response) 
		{
			post = this.repository.getBySlug("este-es-un-slug");
			post.setTitle("This is a title");
			response = this.repository.update(post);
		}
		
		if(response) 
		{
			post = this.repository.getBySlug("este-es-un-slug");
			Assert.assertEquals("This is a title", post.getTitle());
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
		Category category = this.mockupCategory();
		
		Post post = new Post();
		post.setSlug("este-es-un-slug");
		post.setTitle("Esto es un título");
		post.setExcerpt("Lorem ipsum...");
		post.setFeatured_image_url("/path/to/an/image");
		post.setType("Esto discrimina los publicaciones");
		post.setUser_id(user.getId());
		post.setCategory_id(category.getId());
		boolean response = this.repository.save(post);
		
		if(response) 
		{
			post = this.repository.getBySlug("este-es-un-slug");
			response = this.repository.delete(post);
		}
		
		Assert.assertTrue(response);
	}

	private Category mockupCategory() 
	{
		Category category = new Category();
		category.setName("Categoria ZZZ");
		category.setDescription("Lorem ipsum...");
		this.categoryRepository.save(category);
		
		return this.categoryRepository.getByName("Categoria ZZZ");
	}

	private User mockupUser() 
	{
		User user = new User();
		user.setEmail("nombre@email.com");
		user.setFirstname("Nombre");
		user.setLastname("Apellido");
		user.setPassword("estoES1Contraseña");
		this.userRepository.save(user);
		return this.userRepository.getByEmail("nombre@email.com");
	}
}

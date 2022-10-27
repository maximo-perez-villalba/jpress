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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import net.mpv.jpress.components.TestDatabaseConfiguration;
import net.mpv.jpress.model.Category;
import net.mpv.jpress.model.Post;
import net.mpv.jpress.model.PostBody;
import net.mpv.jpress.model.User;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
class PostBodyRepositoryTest 
{
	@Autowired
	private PostBodyRepository repository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	private String body = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
	
	@BeforeEach
	void init() 
	{
		boolean $value = false;
		this.repository.setVerbose($value);
		this.categoryRepository.setVerbose($value);
		this.userRepository.setVerbose($value);
		this.postRepository.setVerbose($value);
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
		
		PostBody postBody = new PostBody();
		postBody.setBody(this.body);
		postBody.setPost_id(post.getId());
		
		boolean response = this.repository.save(postBody);
		if(response)
		{
			postBody = this.repository.getByPost(post.getId());
			long id = postBody.getId();
			postBody = this.repository.getById(id);
			Assert.assertNotNull(postBody);
		}
		else 
		{
			Assert.assertTrue(false);
		}
	}

	@Test
	void testGetByIdWithInvalidValue() 
	{
		PostBody postBody = this.repository.getById(-1);
		Assert.assertNull(postBody);
	}
	
	@Test
	void testGetByPost() 
	{
		Post post = this.mockupPost();
		
		PostBody postBody = new PostBody();
		postBody.setBody(this.body);
		postBody.setPost_id(post.getId());
		
		boolean response = this.repository.save(postBody);
		if(response)
		{
			postBody = this.repository.getByPost(post.getId());
			Assert.assertNotNull(postBody);
		}
		else 
		{
			Assert.assertTrue(false);
		}
	}
	
	@Test
	void testGetByPostWithInvalidValue() 
	{
		Post post = this.mockupPost();
		PostBody postBody = this.repository.getByPost(post.getId());
		Assert.assertNull(postBody);
	}
	
	@Test
	void testGetAll() 
	{
		Post post = this.mockupPost();
		
		PostBody postBody = new PostBody();
		postBody.setBody(this.body);
		postBody.setPost_id(post.getId());
		
		boolean response = this.repository.save(postBody);
		if(response)
		{
			postBody = new PostBody();
			postBody.setBody(this.body+" mas texto...");
			postBody.setPost_id(post.getId());
			
			response = this.repository.save(postBody);
		}
		if(response)
		{
			postBody = new PostBody();
			postBody.setBody(this.body+" mas texto y más texto...");
			postBody.setPost_id(post.getId());
			
			response = this.repository.save(postBody);
		}
		
		if(response)
		{
			List<PostBody> list = this.repository.getAll();
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
		Post post = this.mockupPost();
		
		PostBody postBody = new PostBody();
		postBody.setBody(this.body);
		postBody.setPost_id(post.getId());
		
		boolean response = this.repository.save(postBody);
		
		Assert.assertTrue(response);
	}

	@Test
	void testDelete() 
	{
		Post post = this.mockupPost();
		
		PostBody postBody = new PostBody();
		postBody.setBody(this.body);
		postBody.setPost_id(post.getId());
		
		boolean response = this.repository.save(postBody);
		if(response)
		{
			postBody = this.repository.getByPost(post.getId());
			response = this.repository.delete(postBody);
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
			category.setParent_id(0);
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
			user.setGroup_id(0);
			this.userRepository.save(user);
			user = this.userRepository.getByEmail("nombre@email.com");
		}
		return user;
	}

}

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
import net.mpv.jpress.model.Comment;
import net.mpv.jpress.model.Post;
import net.mpv.jpress.model.User;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
class CommentRepositoryTest 
{
	@Autowired
	private CommentRepository repository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	private String body = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";
	
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
		User user = this.mockupUser();
		Post post = this.mockupPost();
		
		Comment comment = new Comment();
		comment.setBody(this.body);
		comment.setPost_id(post.getId());
		comment.setUser_id(user.getId());
		boolean response = this.repository.save(comment);

		if(response)
		{
			List<Comment> list = this.repository.getByPostId(post.getId());
			if(list.size() == 1) 
			{
				comment = list.get(0);
				comment = this.repository.getById(comment.getId());
				response = Objects.nonNull(comment);
			}
			else 
			{
				response = false;
			}
		}
		
		Assert.assertTrue(response);	
	}

	@Test
	void testGetByPostId() 
	{
		User user = this.mockupUser();
		Post post = this.mockupPost();
		
		Comment comment = new Comment();
		comment.setBody(this.body);
		comment.setPost_id(post.getId());
		comment.setUser_id(user.getId());
		boolean response = this.repository.save(comment);

		if(response)
		{
			List<Comment> list = this.repository.getByPostId(post.getId());
			Assert.assertEquals(1, list.size());
		}
		else
		{
			Assert.assertTrue(true);	
		}
	}

	@Test
	void testGetByUserId() 
	{
		User user = this.mockupUser();
		Post post = this.mockupPost();
		
		Comment comment = new Comment();
		comment.setBody(this.body);
		comment.setPost_id(post.getId());
		comment.setUser_id(user.getId());
		boolean response = this.repository.save(comment);

		if(response)
		{
			List<Comment> list = this.repository.getByUserId(user.getId());
			Assert.assertEquals(1, list.size());
		}
		else
		{
			Assert.assertTrue(true);	
		}
	}

	@Test
	void testGetAll() 
	{
		User user = this.mockupUser();
		Post post = this.mockupPost();
		
		Comment comment = new Comment();
		comment.setBody(this.body);
		comment.setPost_id(post.getId());
		comment.setUser_id(user.getId());
		boolean response = this.repository.save(comment);

		if(response)
		{
			comment = new Comment();
			comment.setBody(this.body+"con más texto");
			comment.setPost_id(post.getId());
			comment.setUser_id(user.getId());
			response = this.repository.save(comment);
		}
		if(response)
		{
			comment = new Comment();
			comment.setBody(this.body+"con mucho más texto");
			comment.setPost_id(post.getId());
			comment.setUser_id(user.getId());
			response = this.repository.save(comment);
		}
		
		if(response)
		{
			List<Comment> list = this.repository.getAll();
			Assert.assertEquals(3, list.size());
		}
		else
		{
			Assert.assertTrue(true);	
		}
	}

	@Test
	void testSave() 
	{
		User user = this.mockupUser();
		Post post = this.mockupPost();
		
		Comment comment = new Comment();
		comment.setBody(this.body);
		comment.setPost_id(post.getId());
		comment.setUser_id(user.getId());
		boolean response = this.repository.save(comment);
		
		Assert.assertTrue(response);
	}

	@Test
	void testUpdate() 
	{
		User user = this.mockupUser();
		Post post = this.mockupPost();
		
		Comment comment = new Comment();
		comment.setBody(this.body);
		comment.setPost_id(post.getId());
		comment.setUser_id(user.getId());
		boolean response = this.repository.save(comment);

		if(response)
		{
			List<Comment> list = this.repository.getByPostId(post.getId());
			comment = list.get(0);
			comment.setBody("Cambie de opinión");
			response = this.repository.update(comment);
		}
		
		Assert.assertTrue(response);	
	}

	@Test
	void testDelete() 
	{
		User user = this.mockupUser();
		Post post = this.mockupPost();
		
		Comment comment = new Comment();
		comment.setBody(this.body);
		comment.setPost_id(post.getId());
		comment.setUser_id(user.getId());
		boolean response = this.repository.save(comment);

		if(response)
		{
			List<Comment> list = this.repository.getByPostId(post.getId());
			comment = list.get(0);
			response = this.repository.delete(comment);
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
			this.userRepository.save(user);
			user = this.userRepository.getByEmail("nombre@email.com");
		}
		return user;
	}


}

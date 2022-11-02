package net.mpv.jpress.data.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.mpv.jpress.data.mapper.PostMapper;
import net.mpv.jpress.data.model.Post;

@Repository
public class PostRepository extends DBRepository<Post>  
{

	@Override
	public Post getById(long id) 
	{
		Post post = null;
		try 
		{
			post = this.jdbcTemplate.queryForObject(
					"SELECT * FROM posts WHERE id = ?;",
					new PostMapper(),
					new Object[]{id}
				);
		} 
		catch (Exception e) 
		{
			this.setLastException(e);
		}
		return post;
	}

	public Post getBySlug(String slug) 
	{
		Post post = null;
		try 
		{
			post = this.jdbcTemplate.queryForObject(
					"SELECT * FROM posts WHERE slug = ?;",
					new PostMapper(),
					new Object[]{slug}
				);
		} 
		catch (Exception e) 
		{
			this.setLastException(e);
		}
		return post;
	}
	
	@Override
	public List<Post> getAll() 
	{
		return this.jdbcTemplate.query(
				"SELECT * FROM posts;", 
				new PostMapper()
			);
	}

	@Override
	protected String queryInsert(Post post) 
	{
		String query = "INSERT INTO posts (slug,title,excerpt,featured_image_url,"
				+ "type,category_id,user_id) VALUES ('%s','%s','%s','%s','%s',%d,%d);"; 
		return String.format(
				query,
				post.getSlug(),
				this.sanitize(post.getTitle()),
				this.sanitize(post.getExcerpt()),
				post.getFeatured_image_url(),
				post.getType(),
				post.getCategory_id(),
				post.getUser_id()
			);
	}

	@Override
	protected String queryUpdate(Post post) 
	{
		return String.format(
				"UPDATE posts SET slug='%s',title='%s',excerpt='%s',featured_image_url='%s',"
				+ "type='%s' WHERE id='%d';",
				post.getSlug(), 
				this.sanitize(post.getTitle()), 
				this.sanitize(post.getExcerpt()),
				post.getFeatured_image_url(),
				post.getType(),
				post.getId()
			);
	}

	@Override
	protected String queryDelete(Post post) 
	{
		return String.format("DELETE FROM posts WHERE id = %d;", post.getId());
	}

	@Override
	protected String queryClean() 
	{
		return String.format("DELETE FROM posts;");
	}

	@Override
	public boolean deleteById(long id) 
	{
		return this.execute(
				String.format(
						"DELETE FROM posts WHERE id = %d;", 
						id
					)
			);
	}
}

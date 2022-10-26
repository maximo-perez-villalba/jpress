package net.mpv.jpress.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.mpv.jpress.mapper.PostMapper;
import net.mpv.jpress.model.Post;

@Repository
public class PostRepository extends DBRepository<Post>  
{

	@Override
	public Post getById(long id) 
	{
		return this.jdbcTemplate.queryForObject(
				"SELECT * FROM posts WHERE id = ?;",
				new PostMapper(),
				new Object[]{id}
			);
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
		return String.format(
				"INSERT INTO posts (slug,title,excerpt,featured_image_url,type,category_id,user_id) "
				+ "VALUES ('%s','%s','%s','%s','%d','%d');",
				post.getSlug(),
				post.getTitle(),
				post.getExcerpt(),
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
				+ "type='%s',category_id='%d',user_id='%d' WHERE id='%d';",
				post.getSlug(), 
				post.getTitle(), 
				post.getExcerpt(),
				post.getFeatured_image_url(),
				post.getType(),
				post.getCategory_id(),
				post.getUser_id(),
				post.getId()
			);
	}

	@Override
	protected String queryDelete(Post post) 
	{
		return String.format("DELETE FROM posts WHERE id='%d';", post.getId());
	}

	@Override
	protected String queryClean() 
	{
		return String.format("DELETE FROM posts;");
	}
}

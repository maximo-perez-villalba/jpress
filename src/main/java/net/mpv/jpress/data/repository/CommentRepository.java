package net.mpv.jpress.data.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.mpv.jpress.data.mapper.CommentMapper;
import net.mpv.jpress.data.model.Comment;

@Repository
public class CommentRepository extends DBRepository<Comment> 
{

	@Override
	public Comment getById(long id) 
	{
		Comment comment  = null;
		try 
		{
			comment = this.jdbcTemplate.queryForObject(
					"SELECT * FROM comments WHERE id = ?;",
					new CommentMapper(),
					new Object[]{id}
				); 
		} 
		catch (Exception e) 
		{
			this.setLastException(e);
		}
		
		return comment;
	}

	public Comment getByData(long postId, long userId, String body) 
	{
		Comment comment  = null;
		try 
		{
			comment = this.jdbcTemplate.queryForObject(
					"SELECT * FROM comments WHERE "
					+ "post_id = ? AND user_id = ? AND body = ?;",
					new CommentMapper(),
					new Object[]{postId,userId,body}
				); 
		} 
		catch (Exception e) 
		{
			this.setLastException(e);
		}
		
		return comment;
	}
	
	public List<Comment> getByPostId(long id) 
	{
		String sql = String.format(
				"SELECT * FROM comments WHERE post_id = %d;", 
				id
			);
		return this.jdbcTemplate.query(sql,new CommentMapper());
	}

	public List<Comment> getByUserId(long id) 
	{
		String sql = String.format(
				"SELECT * FROM comments WHERE user_id = %d;", 
				id
			);
		return this.jdbcTemplate.query(sql,new CommentMapper());
	}

	@Override
	public List<Comment> getAll() 
	{
		return this.jdbcTemplate.query(
				"SELECT * FROM comments;", 
				new CommentMapper()
			);
	}

	@Override
	protected String queryInsert(Comment comment) 
	{
		return String.format(
				"INSERT INTO comments (body,post_id,user_id) VALUES ('%s',%d,%d);",
				this.sanitize(comment.getBody()), 
				comment.getPost_id(),
				comment.getUser_id()
			);
	}

	@Override
	protected String queryUpdate(Comment comment) 
	{
		return String.format(
				"UPDATE comments SET body='%s' WHERE id = %d;",
				this.sanitize(comment.getBody()), 
				comment.getId()
			);
	}

	@Override
	protected String queryDelete(Comment comment) 
	{
		return String.format("DELETE FROM comments WHERE id = %d;", comment.getId());
	}

	@Override
	protected String queryClean() 
	{
		return String.format("DELETE FROM comments;");	
	}

	@Override
	public boolean deleteById(long id) 
	{
		return this.execute(
				String.format(
						"DELETE FROM comments WHERE id = %d;", 
						id
					)
				);
	}

}

package net.mpv.jpress.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.mpv.jpress.mapper.CommentMapper;
import net.mpv.jpress.model.Comment;

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
				comment.getBody(), 
				comment.getPost_id(),
				comment.getUser_id()
			);
	}

	@Override
	protected String queryUpdate(Comment comment) 
	{
		return String.format(
				"UPDATE comments SET body='%s' WHERE id=%d;",
				comment.getBody(), 
				comment.getId()
			);
	}

	@Override
	protected String queryDelete(Comment comment) 
	{
		return String.format("DELETE FROM comments WHERE id=%d;", comment.getId());
	}

	@Override
	protected String queryClean() 
	{
		return String.format("DELETE FROM comments;");	
	}

}

package net.mpv.jpress.repository;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.mpv.jpress.mapper.CategoryMapper;
import net.mpv.jpress.mapper.CommentMapper;
import net.mpv.jpress.model.Category;
import net.mpv.jpress.model.Comment;

@Repository
public class CommentRepository extends DBRepository<Comment> 
{

	@Override
	public Comment getById(long id) 
	{
		return this.jdbcTemplate.queryForObject(
				"SELECT * FROM comments WHERE id = ?;",
				new CommentMapper(),
				new Object[]{id}
			);
	}

	@Override
	public List<Comment> getAll(Pageable pageable) 
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
				"INSERT INTO comments (body,post_id,user_id) VALUES ('%s','%d','%d');",
				comment.getBody(), 
				comment.getPost_id(),
				comment.getUser_id()
			);
	}

	@Override
	protected String queryUpdate(Comment comment) 
	{
		return String.format(
				"UPDATE comments SET body='%s' WHERE id='%d';",
				comment.getBody(), 
				comment.getId()
			);
	}

	@Override
	protected String queryDelete(Comment comment) 
	{
		return String.format("DELETE FROM comments WHERE id='%d';", comment.getId());
	}

}

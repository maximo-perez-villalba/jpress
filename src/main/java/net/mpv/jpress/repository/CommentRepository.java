package net.mpv.jpress.repository;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.mpv.jpress.model.Comment;

@Repository
public class CommentRepository extends DBRepository<Comment> 
{

	@Override
	public List<Comment> getAll(Pageable pageable) 
	{
		List<Comment> list = new ArrayList<Comment>() ;
		return list;
	}

	@Override
	protected String queryInsert(Comment comment) 
	{
		return String.format(
				"INSERT INTO comments (comment,post_id,user_id) VALUES ('%s','%d','%d');",
				comment.getComment(), 
				comment.getPost_id(),
				comment.getUser_id()
			);
	}

	@Override
	protected String queryUpdate(Comment comment) 
	{
		return String.format(
				"UPDATE comments SET comment='%s' WHERE id='%d';",
				comment.getComment(), 
				comment.getId()
			);
	}

	@Override
	protected String queryDelete(Comment comment) 
	{
		return String.format("DELETE FROM comments WHERE id='%d';", comment.getId());
	}

}

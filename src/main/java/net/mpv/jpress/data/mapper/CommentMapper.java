package net.mpv.jpress.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.mpv.jpress.data.model.Comment;

public class CommentMapper implements RowMapper<Comment> 
{

	@Override
	public Comment mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Comment comment = new Comment();
		comment.setId(rs.getLong("id"));
		comment.setBody(rs.getString("body"));
		comment.setCreated(rs.getDate("created"));
		comment.setPost_id(rs.getLong("post_id"));
		comment.setUser_id(rs.getLong("user_id"));
		return comment;
	}

}

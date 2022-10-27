package net.mpv.jpress.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.mpv.jpress.model.PostBody;

public class PostBodyMapper implements RowMapper<PostBody> 
{

	@Override
	public PostBody mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		PostBody body = new PostBody();
		body.setId(rs.getLong("id"));
		body.setBody(rs.getString("body"));
		body.setCreated(rs.getDate("created"));
		body.setPost_id(rs.getLong("post_id"));
		return body;
	}

}

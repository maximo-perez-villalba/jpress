package net.mpv.jpress.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.mpv.jpress.data.model.Post;

public class PostMapper implements RowMapper<Post> 
{

	@Override
	public Post mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Post post = new Post();
		post.setId(rs.getLong("id"));
		post.setSlug(rs.getString("slug"));
		post.setTitle(rs.getString("title"));
		post.setExcerpt(rs.getString("excerpt"));
		post.setFeatured_image_url(rs.getString("featured_image_url"));
		post.setType(rs.getString("type"));
		post.setCategory_id(rs.getLong("category_id"));
		post.setUser_id(rs.getLong("user_id"));
		return post;
	}

}

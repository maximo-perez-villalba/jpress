package net.mpv.jpress.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.mpv.jpress.data.model.PostMetaData;

public class PostMetaDataMapper implements RowMapper<PostMetaData> 
{

	@Override
	public PostMetaData mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		PostMetaData metadata = new PostMetaData();
		metadata.setId(rs.getLong("id"));
		metadata.setKey(rs.getString("key"));
		metadata.setValue(rs.getString("value"));
		metadata.setPost_id(rs.getLong("post_id"));
		return metadata;
	}

}

package net.mpv.jpress.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.mpv.jpress.model.UserMetaData;

public class UserMedataMapper implements RowMapper<UserMetaData> 
{

	@Override
	public UserMetaData mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		UserMetaData metadata = new UserMetaData();
		metadata.setId(rs.getLong("id"));
		metadata.setKey(rs.getString("key"));
		metadata.setValue(rs.getString("value"));
		metadata.setUsers_id(rs.getLong("users_id"));
		return metadata;
	}

}

package net.mpv.jpress.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.mpv.jpress.model.Group;

public class GroupMapper implements RowMapper<Group> 
{

	@Override
	public Group mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Group group = new Group();
		group.setId(rs.getLong("id"));
		group.setName(rs.getString("name"));
		return group;
	}

}

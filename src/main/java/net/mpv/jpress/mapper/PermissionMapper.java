package net.mpv.jpress.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.mpv.jpress.model.Permission;

public class PermissionMapper implements RowMapper<Permission> 
{

	@Override
	public Permission mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Permission permission = new Permission();
		permission.setId(rs.getLong("id"));
		permission.setName(rs.getString("name"));
		return permission;
	}

}

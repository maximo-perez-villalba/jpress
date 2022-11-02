package net.mpv.jpress.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.mpv.jpress.data.model.User;

public class UserMapper implements RowMapper<User> 
{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setFirstname(rs.getString("firstname"));
		user.setLastname(rs.getString("lastname"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		return user;
	}

}

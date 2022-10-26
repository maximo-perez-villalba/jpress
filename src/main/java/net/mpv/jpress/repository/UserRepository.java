package net.mpv.jpress.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.mpv.jpress.mapper.UserMapper;
import net.mpv.jpress.model.User;

@Repository
public class UserRepository extends DBRepository<User> 
{

	@Override
	public User getById(long id) 
	{
		return this.jdbcTemplate.queryForObject(
				"SELECT * FROM `users` WHERE id = ?;",
				new UserMapper(),
				new Object[]{id}
			);
	}

	public User getByEmail(String email) 
	{
		return this.jdbcTemplate.queryForObject(
				"SELECT * FROM `users` WHERE email = ?;",
				new UserMapper(),
				new Object[]{email}
			);
	}

	@Override
	public List<User> getAll() 
	{
		return this.jdbcTemplate.query(
				"SELECT * FROM `users`;", 
				new UserMapper()
			);
	}

	@Override
	protected String queryInsert(User user) 
	{
		return String.format(
				"INSERT INTO `users` (firstname,lastname,email,password,group_id)"
				+ " VALUES ('%s','%s','%s','%s','%d');",
				user.getFirstname(),
				user.getLastname(),
				user.getEmail(),
				user.getPassword(),
				user.getGroup_id()
			);
	}

	@Override
	protected String queryUpdate(User user) 
	{
		return String.format(
				"UPDATE `users` SET firstname='%s',lastname='%s',email='%s',"
				+ "`password`='%s',group_id='%d' WHERE id='%d';",
				user.getFirstname(),
				user.getLastname(),
				user.getEmail(),
				user.getPassword(),
				user.getGroup_id(),
				user.getId()
			);
	}

	@Override
	protected String queryDelete(User user) 
	{
		return String.format("DELETE FROM `users` WHERE id='%d';", user.getId());
	}

	@Override
	protected String queryClean() 
	{
		return String.format("DELETE FROM `users`;");
	}
	
}

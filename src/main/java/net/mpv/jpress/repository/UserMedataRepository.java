package net.mpv.jpress.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.mpv.jpress.mapper.UserMedataMapper;
import net.mpv.jpress.model.UserMetaData;

@Repository
public class UserMedataRepository extends DBRepository<UserMetaData> 
{

	@Override
	public UserMetaData getById(long id) 
	{
		return this.jdbcTemplate.queryForObject(
				"SELECT * FROM users_metadata WHERE id = ?;",
				new UserMedataMapper(),
				new Object[]{id}
			);
	}

	@Override
	public List<UserMetaData> getAll(Pageable pageable) 
	{
		return this.jdbcTemplate.query(
				"SELECT * FROM users_metadata;", 
				new UserMedataMapper()
			);
	}

	@Override
	protected String queryInsert(UserMetaData metadata) 
	{
		return String.format(
				"INSERT INTO users_metadata (key,value,users_id) VALUES ('%s','%s','%d');",
				metadata.getKey(),
				metadata.getValue(),
				metadata.getUsers_id()
			);
	}

	@Override
	protected String queryUpdate(UserMetaData metadata) 
	{
		return String.format(
				"UPDATE users_metadata SET value='%s' WHERE id='%d';",
				metadata.getValue(),
				metadata.getId()
			);
	}

	@Override
	protected String queryDelete(UserMetaData metadata) 
	{
		return String.format("DELETE FROM users_metadata WHERE id='%d';",metadata.getId());
	}
	
}

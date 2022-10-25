package net.mpv.jpress.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.mpv.jpress.model.UserMedata;

@Repository
public class UserMedataRepository extends DBRepository<UserMedata> 
{

	@Override
	public List<UserMedata> getAll(Pageable pageable) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String queryInsert(UserMedata metadata) 
	{
		return String.format(
				"INSERT INTO users_metadata (key,value,users_id) VALUES ('%s','%s','%d');",
				metadata.getKey(),
				metadata.getValue(),
				metadata.getUsers_id()
			);
	}

	@Override
	protected String queryUpdate(UserMedata metadata) 
	{
		return String.format(
				"UPDATE users_metadata SET value='%s' WHERE id='%d';",
				metadata.getValue(),
				metadata.getId()
			);
	}

	@Override
	protected String queryDelete(UserMedata metadata) 
	{
		return String.format("DELETE FROM users_metadata WHERE id='%d';",metadata.getId());
	}
	
}

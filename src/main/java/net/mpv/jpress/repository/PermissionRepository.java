package net.mpv.jpress.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.mpv.jpress.mapper.PermissionMapper;
import net.mpv.jpress.model.Permission;

@Repository
public class PermissionRepository extends  DBRepository<Permission> 
{

	@Override
	public Permission getById(long id) 
	{
		return this.jdbcTemplate.queryForObject(
				"SELECT * FROM permissions WHERE id = ?;",
				new PermissionMapper(),
				new Object[]{id}
			);
	}

	@Override
	public List<Permission> getAll() 
	{
		return this.jdbcTemplate.query(
				"SELECT * FROM permissions;", 
				new PermissionMapper()
			);
	}

	@Override
	protected String queryInsert(Permission permission) 
	{
		return String.format(
				"INSERT INTO permissions (name) VALUES ('%s');",
				permission.getName()
			);
	}

	@Override
	protected String queryUpdate(Permission permission) 
	{
		return String.format(
				"UPDATE permissions SET name='%s' WHERE id='%d';",
				permission.getName(),
				permission.getId()
			);
	}

	@Override
	protected String queryDelete(Permission permission) 
	{
		return String.format("DELETE FROM permissions WHERE id='%d';", permission.getId());
	}

	@Override
	protected String queryClean() 
	{
		return String.format("DELETE FROM permissions;");
	}

}

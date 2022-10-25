package net.mpv.jpress.repository;

import java.awt.print.Pageable;
import java.util.List;
import org.springframework.stereotype.Repository;
import net.mpv.jpress.model.Permission;

@Repository
public class PermissionRepository extends  DBRepository<Permission> 
{

	@Override
	public List<Permission> getAll(Pageable pageable) 
	{
		// TODO Auto-generated method stub
		return null;
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

}

package net.mpv.jpress.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.mpv.jpress.model.Group;

@Repository
public class GroupRepository extends DBRepository<Group>
{

	@Override
	public List<Group> getAll(Pageable pageable) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String queryInsert(Group group) 
	{
		return String.format(
				"INSERT INTO groups (name) VALUES ('%s');",
				group.getName()
			);
	}

	@Override
	protected String queryUpdate(Group group) 
	{
		return String.format(
				"UPDATE groups SET name='%s' WHERE id='%d';",
				group.getName(),
				group.getId()
			);
	}

	@Override
	protected String queryDelete(Group group) 
	{
		return String.format("DELETE FROM groups WHERE id='%d';", group.getId());
	}

}

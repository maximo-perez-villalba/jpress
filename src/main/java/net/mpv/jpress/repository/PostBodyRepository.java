package net.mpv.jpress.repository;

import java.awt.print.Pageable;
import java.util.List;
import org.springframework.stereotype.Repository;
import net.mpv.jpress.model.PostBody;

@Repository
public class PostBodyRepository extends DBRepository<PostBody>
{

	@Override
	public List<PostBody> getAll(Pageable pageable) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String queryInsert(PostBody body) 
	{
		return String.format(
				"INSERT INTO post_bodies (body,post_id) VALUES ('%s','%d');",
				body.getBody(),
				body.getPost_id()
			);
	}

	@Override
	protected String queryUpdate(PostBody body) 
	{
		return String.format(
				"UPDATE post_bodies SET body='%s' WHERE id='%d';",
				body.getBody(),
				body.getId()
			);
	}

	@Override
	protected String queryDelete(PostBody body) 
	{
		return String.format("DELETE FROM post_bodies WHERE id='%d';", body.getId());
	}
}

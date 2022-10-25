package net.mpv.jpress.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.mpv.jpress.mapper.PostBodyMapper;
import net.mpv.jpress.model.PostBody;

@Repository
public class PostBodyRepository extends DBRepository<PostBody>
{

	@Override
	public PostBody getById(long id) 
	{
		return this.jdbcTemplate.queryForObject(
				"SELECT * FROM post_bodies WHERE id = ?;",
				new PostBodyMapper(),
				new Object[]{id}
			);
	}

	@Override
	public List<PostBody> getAll(Pageable pageable) 
	{
		return this.jdbcTemplate.query(
				"SELECT * FROM post_bodies;", 
				new PostBodyMapper()
			);
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

package net.mpv.jpress.data.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.mpv.jpress.data.mapper.PostMetaDataMapper;
import net.mpv.jpress.data.model.PostMetaData;

@Repository
public class PostMetaDataRepository extends DBRepository<PostMetaData> 
{

	@Override
	public PostMetaData getById(long id) 
	{
		PostMetaData metadata = null;
		try 
		{
			metadata = this.jdbcTemplate.queryForObject(
					"SELECT * FROM posts_metadata WHERE id = ?;",
					new PostMetaDataMapper(),
					new Object[]{id}
				);
		} 
		catch (Exception e) 
		{
			this.setLastException(e);
		}
		return metadata;
	}
	
	public PostMetaData getByKey(String key, long postId) 
	{
		PostMetaData metadata = null;
		try 
		{
			metadata = this.jdbcTemplate.queryForObject(
					"SELECT * FROM posts_metadata WHERE `key` = ? AND post_id = ?;",
					new PostMetaDataMapper(),
					new Object[]{key, postId}
				);
		} 
		catch (Exception e) 
		{
			this.setLastException(e);
		}
		return metadata;
	}

	@Override
	public List<PostMetaData> getAll() 
	{
		return this.jdbcTemplate.query(
				"SELECT * FROM posts_metadata;", 
				new PostMetaDataMapper()
			);
	}

	@Override
	protected String queryInsert(PostMetaData metadata) 
	{
		return String.format(
				"INSERT INTO posts_metadata (`key`,`value`,post_id) VALUES ('%s','%s','%d');",
				metadata.getKey(),
				this.sanitize(metadata.getValue()),
				metadata.getPost_id()
			);
	}

	@Override
	protected String queryUpdate(PostMetaData metadata) 
	{
		return String.format(
				"UPDATE posts_metadata SET value='%s' WHERE id = %d;",
				this.sanitize(metadata.getValue()),
				metadata.getId()
			);
	}

	@Override
	protected String queryDelete(PostMetaData metadata) 
	{
		return String.format("DELETE FROM posts_metadata WHERE id = %d;", metadata.getId());	
	}

	@Override
	protected String queryClean() 
	{
		return String.format("DELETE FROM posts_metadata;");	
	}

	@Override
	public boolean deleteById(long id) 
	{
		return this.execute(
				String.format(
						"DELETE FROM posts_metadata WHERE id = %d;", 
						id
					)
			);
	}
}

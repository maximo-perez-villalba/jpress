package net.mpv.jpress.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.mpv.jpress.model.PostMetaData;

@Repository
public class PostMetaDataRepository extends DBRepository<PostMetaData> 
{

	@Override
	public List<PostMetaData> getAll(Pageable pageable) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String queryInsert(PostMetaData metadata) 
	{
		return String.format(
				"INSERT INTO posts_metadata (key,value,post_id) VALUES ('%s','%s','%d');",
				metadata.getKey(),
				metadata.getValue(),
				metadata.getPost_id()
			);
	}

	@Override
	protected String queryUpdate(PostMetaData metadata) 
	{
		return String.format(
				"UPDATE posts_metadata SET value='%s' WHERE id='%d';",
				metadata.getValue(),
				metadata.getId()
			);
	}

	@Override
	protected String queryDelete(PostMetaData metadata) 
	{
		return String.format("DELETE FROM posts_metadata WHERE id='%d';", metadata.getId());	
	}
}

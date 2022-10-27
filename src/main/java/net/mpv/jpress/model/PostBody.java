package net.mpv.jpress.model;

import java.util.Date;

public class PostBody extends DBModel 
{
	private String body;
	
	private Date created;
	
	private long post_id;

	public String getBody() 
	{
		return body;
	}

	public void setBody(String body) 
	{
		this.body = body;
	}

	public long getPost_id() 
	{
		return post_id;
	}

	public void setPost_id(long post_id) 
	{
		this.post_id = post_id;
	}

	public Date getCreated() 
	{
		return created;
	}

	public void setCreated(Date created) 
	{
		this.created = created;
	}
	
}

package net.mpv.jpress.model;

import java.util.Date;

public class Comment extends DBModel 
{
	private String comment;
	
	private Date created;
	
	private long post_id;
	
	private long user_id;

	public String getComment() 
	{
		return comment;
	}

	public void setComment(String comment) 
	{
		this.comment = comment;
	}

	public Date getCreated() 
	{
		return created;
	}

	public void setCreated(Date created) 
	{
		this.created = created;
	}

	public long getPost_id() 
	{
		return post_id;
	}

	public void setPost_id(long post_id) 
	{
		this.post_id = post_id;
	}

	public long getUser_id() 
	{
		return user_id;
	}

	public void setUser_id(long user_id) 
	{
		this.user_id = user_id;
	}
	
	
}

package net.mpv.jpress.data.model;

import java.util.Date;

public class Category extends DBModel
{
	private String name;
	
	private String description;
	
	private Date created;

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
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

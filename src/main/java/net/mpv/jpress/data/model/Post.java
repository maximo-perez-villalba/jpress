package net.mpv.jpress.data.model;

public class Post extends DBModel 
{
	private String slug;
	
	private String title;
	
	private String excerpt;
	
	private String featured_image_url;
	
	private String type;
	
	private long category_id;
	
	private long user_id;

	public String getSlug() 
	{
		return slug;
	}

	public void setSlug(String slug) 
	{
		this.slug = slug;
	}

	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getExcerpt() 
	{
		return excerpt;
	}

	public void setExcerpt(String excerpt) 
	{
		this.excerpt = excerpt;
	}

	public String getFeatured_image_url() 
	{
		return featured_image_url;
	}

	public void setFeatured_image_url(String featured_image_url) 
	{
		this.featured_image_url = featured_image_url;
	}

	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}

	public long getCategory_id() 
	{
		return category_id;
	}

	public void setCategory_id(long category_id) 
	{
		this.category_id = category_id;
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

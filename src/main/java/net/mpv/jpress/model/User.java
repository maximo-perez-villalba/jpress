package net.mpv.jpress.model;

public class User extends DBModel 
{
	private String firstname;
	
	private String lastname;
	
	private String email;
	
	private String password;
	
	private long group_id;

	public String getFirstname() 
	{
		return firstname;
	}

	public void setFirstname(String firstname) 
	{
		this.firstname = firstname;
	}

	public String getLastname() 
	{
		return lastname;
	}

	public void setLastname(String lastname) 
	{
		this.lastname = lastname;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public long getGroup_id() 
	{
		return group_id;
	}

	public void setGroup_id(long group_id) 
	{
		this.group_id = group_id;
	}
}

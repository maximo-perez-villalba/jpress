package net.mpv.jpress.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import net.mpv.jpress.model.DBModel;

public abstract class DBRepository<T> 
{

	@Autowired
	private DataSource dataSource;
	
	protected JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void postConstructor() 
	{
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}
	
	private Exception lastException;
	
	public Exception getLastException() 
	{
		return lastException;
	}

	public void setLastException(Exception lastException) 
	{
		this.lastException = lastException;
	}
	
	public T findById(long id)
	{
		return null;
	}
	
	private boolean execute(String sql)
	{
		boolean response = false;
		try
		{
			this.jdbcTemplate.execute(sql);
			response = true;
		}
		catch(Exception exception)
		{
			this.setLastException(exception);
		}
		return response;
	}

	public boolean clean()
	{
		return this.execute(this.queryClean());
	}
	
	abstract protected String queryClean();
	
	abstract protected String queryInsert(T t);
	
	abstract protected String queryUpdate(T t);
	
	abstract protected String queryDelete(T t);
	
	abstract public List<T> getAll();
	
	abstract public T getById(long id);
	
	public boolean save(T t) 
	{
		boolean response = false;
		DBModel model = (DBModel)t;
		if(model.getId() == 0)
		{
			response = this.execute(this.queryInsert(t));
		}
		return response;
	}
	
	public boolean update(T t) 
	{
		boolean response = false;
		DBModel model = (DBModel)t;
		if(model.getId() != 0)
		{
			response = this.execute(this.queryUpdate(t));
		}
		return response;
	}
	
	public boolean delete(T t) 
	{
		boolean response = false;
		DBModel model = (DBModel)t;
		if(model.getId() != 0)
		{
			response = this.execute(this.queryDelete(t));
		}
		return response;
	}
	
}

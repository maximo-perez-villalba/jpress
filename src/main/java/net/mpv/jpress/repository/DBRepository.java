package net.mpv.jpress.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import net.mpv.jpress.model.DBModel;

public abstract class DBRepository<T> 
{

	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
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

	abstract protected String queryInsert(T t);
	
	abstract protected String queryUpdate(T t);
	
	abstract protected String queryDelete(T t);
	
	abstract public List<T> getAll(Pageable pageable);
	
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

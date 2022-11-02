package net.mpv.jpress.data.repository;

import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import net.mpv.jpress.data.model.DBModel;

public abstract class DBRepository<T> 
{

	@Autowired
	private DataSource dataSource;
	
	protected JdbcTemplate jdbcTemplate;
	
	private Exception lastException;
	
	private boolean verbose = false;
	
	@PostConstruct
	public void postConstructor() 
	{
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

	public void setVerbose(boolean value) 
	{
		this.verbose = value;
	}

	public boolean getVerbose() 
	{
		return this.verbose;
	}
	
	public Exception getLastException() 
	{
		return lastException;
	}

	public void setLastException(Exception lastException) 
	{
		this.lastException = lastException;
		if(this.getVerbose())
		{
			System.out.println(lastException.getClass()+":");
			System.out.println("message: \""+lastException.getMessage()+"\"");
			for(int i=0; i < 4; i++) 
			{
				StackTraceElement trace = lastException.getStackTrace()[i];
				
				String line = "["+i+"] "+trace.getClassName()+"::"+trace.getMethodName()+"("+trace.getLineNumber()+")";
				System.out.println(line);
			}
			System.out.println();
		}
	}
	
	protected boolean execute(String sql)
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
		if(Objects.nonNull(model) && model.getId() != 0)
		{
			T modelInDB = this.getById(model.getId());
			if(Objects.nonNull(modelInDB)) 
			{
				response = this.execute(this.queryDelete(t));	
			}
		}
		return response;
	}
	
	abstract public boolean deleteById(long id);
	
	/**
	 * @param String value
	 * @return String
	 */
	protected String sanitize(String value) 
	{
		if(Objects.nonNull(value))
		{
			return value.replaceAll("[']", "\'");	
		}
		return null;
	}
	
}

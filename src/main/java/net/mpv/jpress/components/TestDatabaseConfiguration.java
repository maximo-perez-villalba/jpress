package net.mpv.jpress.components;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

//@Configuration
public class TestDatabaseConfiguration extends JPressDatabaseConfiguration
{

	@Override
	@Bean
	public DataSource getDataSource() 
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/tests_jpress");
		dataSource.setUsername("root");
		dataSource.setPassword("admin");
		return dataSource;
	}
}

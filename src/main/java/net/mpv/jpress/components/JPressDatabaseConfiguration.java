package net.mpv.jpress.components;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import net.mpv.jpress.data.repository.CategoryRepository;
import net.mpv.jpress.data.repository.CommentRepository;
import net.mpv.jpress.data.repository.PostBodyRepository;
import net.mpv.jpress.data.repository.PostMetaDataRepository;
import net.mpv.jpress.data.repository.PostRepository;
import net.mpv.jpress.data.repository.UserMetaDataRepository;
import net.mpv.jpress.data.repository.UserRepository;

public abstract class JPressDatabaseConfiguration 
{
	
	abstract public DataSource getDataSource();
	
	@Bean
	public CategoryRepository getCategoryRepository() 
	{
		return new CategoryRepository(); 
	}
	
	@Primary
	@Bean
	public UserRepository getUserRepository() 
	{
		return new UserRepository();
	} 
	
	@Primary
	@Bean
	public UserMetaDataRepository getUserMetaDataRepository() 
	{
		return new UserMetaDataRepository();
	} 
	
	@Primary
	@Bean
	public PostRepository getPostRepository() 
	{
		return new PostRepository();
	} 
	
	@Primary
	@Bean
	public PostMetaDataRepository getPostMetaDataRepository() 
	{
		return new PostMetaDataRepository();
	} 
	
	@Primary
	@Bean
	public PostBodyRepository getPostBodyRepository() 
	{
		return new PostBodyRepository();
	}
	
	@Primary
	@Bean
	public CommentRepository getCommentRepository() 
	{
		return new CommentRepository();
	}

}

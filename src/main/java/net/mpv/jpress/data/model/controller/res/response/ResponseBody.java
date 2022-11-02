package net.mpv.jpress.data.model.controller.res.response;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBody 
{
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date timestamp;
	
	private Object response;
	
	public ResponseBody() {}
	
	public ResponseBody(Object objectResponse) 
	{
		this.response = objectResponse;
		this.timestamp = new Date();
	}
	
	public Date getTimestamp() 
	{
		return timestamp;
	}

	public void setTimestamp(Date timestamp) 
	{
		this.timestamp = timestamp;
	}

	public Object getResponse() 
	{
		return response;
	}

	public void setResponse(Object response) 
	{
		this.response = response;
	}
	
}

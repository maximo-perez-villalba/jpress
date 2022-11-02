package net.mpv.jpress.data.model.controller.res.response;

public class SaveResponseBody extends ResponseBody 
{

	private long id = 0;

	
	/**
	 * @param Object objectResponse
	 * @param long id
	 */
	public SaveResponseBody(Object objectResponse, long id) 
	{
		super(objectResponse);
		this.id = id;
	}

	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}
	
}

package net.mpv.jpress.controller.res;

import org.springframework.http.ResponseEntity;

import net.mpv.jpress.data.model.controller.res.response.ResponseBody;
import net.mpv.jpress.data.model.controller.res.response.SaveResponseBody;

public abstract class GenericResController 
{
	/**
	 * @param Object objectResponse
	 * @return ResponseEntity<ResponseBody>
	 */
	protected ResponseEntity<ResponseBody> ok(Object objectResponse)
	{
		return ResponseEntity.ok(new ResponseBody(objectResponse));
	}

	/**
	 * @param Object objectResponse
	 * @param id
	 * @return ResponseEntity<SaveResponseBody>
	 */
	protected ResponseEntity<SaveResponseBody> saveOk(Object objectResponse, long id)
	{
		return ResponseEntity.ok(new SaveResponseBody(objectResponse, id));
	}

}

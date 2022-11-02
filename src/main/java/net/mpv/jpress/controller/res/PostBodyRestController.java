package net.mpv.jpress.controller.res;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.mpv.jpress.data.model.PostBody;
import net.mpv.jpress.data.model.controller.res.response.ResponseBody;
import net.mpv.jpress.data.model.controller.res.response.SaveResponseBody;
import net.mpv.jpress.data.repository.PostBodyRepository;

@RestController
@RequestMapping("/api/v1/post-body")
public class PostBodyRestController extends GenericResController 
{

	@Autowired
	private PostBodyRepository repository;
	
	@PutMapping
	public ResponseEntity<SaveResponseBody> save(@RequestBody PostBody body)
	{
		this.repository.setVerbose(true);
		boolean response = this.repository.save(body);
		if(response) 
		{
			body = this.repository.getByPost(body.getPost_id());
			if(Objects.nonNull(body)) 
			{
				return this.saveOk(response, body.getId());
			}
		}
		return this.saveOk(false, 0);
	}
	
	@PostMapping
	public ResponseEntity<ResponseBody> update(@RequestBody PostBody body)
	{
		return this.ok(this.repository.update(body));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseBody> delete(@PathVariable long id)
	{
		return this.ok(this.repository.deleteById(id));
	}

	@GetMapping
	public ResponseEntity<List<PostBody>> findAll()
	{
		return ResponseEntity.ok(this.repository.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostBody> findById(@PathVariable long id)
	{
		return ResponseEntity.ok(this.repository.getById(id));
	}
	
}

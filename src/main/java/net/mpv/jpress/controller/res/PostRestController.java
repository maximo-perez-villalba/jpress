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

import net.mpv.jpress.data.model.Post;
import net.mpv.jpress.data.model.controller.res.response.ResponseBody;
import net.mpv.jpress.data.model.controller.res.response.SaveResponseBody;
import net.mpv.jpress.data.repository.PostRepository;

@RestController
@RequestMapping("/api/v1/post")
public class PostRestController extends GenericResController 
{

	@Autowired
	private PostRepository repository;
	
	@PutMapping
	public ResponseEntity<SaveResponseBody> save(@RequestBody Post post)
	{
		this.repository.setVerbose(true);
		boolean response = this.repository.save(post);
		if(response) 
		{
			post = this.repository.getBySlug(post.getSlug());
			if(Objects.nonNull(post)) 
			{
				return this.saveOk(response, post.getId());
			}
		}
		return this.saveOk(false, 0);
	}
	
	@PostMapping
	public ResponseEntity<ResponseBody> update(@RequestBody Post post)
	{
		return this.ok(this.repository.update(post));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseBody> delete(@PathVariable long id)
	{
		return this.ok(this.repository.deleteById(id));
	}

	@GetMapping
	public ResponseEntity<List<Post>> findAll()
	{
		return ResponseEntity.ok(this.repository.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> findById(@PathVariable long id)
	{
		return ResponseEntity.ok(this.repository.getById(id));
	}
	
}

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

import net.mpv.jpress.data.model.Comment;
import net.mpv.jpress.data.model.controller.res.response.ResponseBody;
import net.mpv.jpress.data.model.controller.res.response.SaveResponseBody;
import net.mpv.jpress.data.repository.CommentRepository;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentRestController extends GenericResController 
{

	@Autowired
	private CommentRepository repository;
	
	@PutMapping
	public ResponseEntity<SaveResponseBody> save(@RequestBody Comment comment)
	{
		this.repository.setVerbose(true);
		boolean response = this.repository.save(comment);
		if(response) 
		{
			comment = this.repository.getByData(comment.getPost_id(), comment.getUser_id(), comment.getBody());
			
			if(Objects.nonNull(comment)) 
			{
				return this.saveOk(response, comment.getId());
			}
		}
		return this.saveOk(false, 0);
	}
	
	@PostMapping
	public ResponseEntity<ResponseBody> update(@RequestBody Comment comment)
	{
		return this.ok(this.repository.update(comment));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseBody> delete(@PathVariable long id)
	{
		return this.ok(this.repository.deleteById(id));
	}

	@GetMapping
	public ResponseEntity<List<Comment>> findAll()
	{
		return ResponseEntity.ok(this.repository.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Comment> findById(@PathVariable long id)
	{
		return ResponseEntity.ok(this.repository.getById(id));
	}
	
}

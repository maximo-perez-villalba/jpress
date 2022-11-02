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

import net.mpv.jpress.data.model.User;
import net.mpv.jpress.data.model.controller.res.response.ResponseBody;
import net.mpv.jpress.data.model.controller.res.response.SaveResponseBody;
import net.mpv.jpress.data.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestController extends GenericResController
{
	@Autowired
	private UserRepository repository;
	
	@PutMapping
	public ResponseEntity<SaveResponseBody> save(@RequestBody User user)
	{
		this.repository.setVerbose(true);
		boolean response = this.repository.save(user);
		if(response) 
		{
			user = this.repository.getByEmail(user.getEmail());
			if(Objects.nonNull(user)) 
			{
				return this.saveOk(response, user.getId());
			}
		}
		return this.saveOk(false, 0);
	}
	
	@PostMapping
	public ResponseEntity<ResponseBody> update(@RequestBody User user)
	{
		return this.ok(this.repository.update(user));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseBody> delete(@PathVariable long id)
	{
		return this.ok(this.repository.deleteById(id));
	}

	@GetMapping
	public ResponseEntity<List<User>> findAll()
	{
		return ResponseEntity.ok(this.repository.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable long id)
	{
		return ResponseEntity.ok(this.repository.getById(id));
	}
}

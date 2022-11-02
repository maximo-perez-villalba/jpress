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

import net.mpv.jpress.data.model.Category;
import net.mpv.jpress.data.model.controller.res.response.ResponseBody;
import net.mpv.jpress.data.model.controller.res.response.SaveResponseBody;
import net.mpv.jpress.data.repository.CategoryRepository;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryRestController extends GenericResController
{
	@Autowired
	private CategoryRepository repository;
	
	@PutMapping
	public ResponseEntity<SaveResponseBody> save(@RequestBody Category category)
	{
		this.repository.setVerbose(true);
		boolean response = this.repository.save(category);
		if(response) 
		{
			category = this.repository.getByName(category.getName());
			if(Objects.nonNull(category)) 
			{
				return this.saveOk(response, category.getId());
			}
		}
		return this.saveOk(false, 0);
	}
	
	@PostMapping
	public ResponseEntity<ResponseBody> update(@RequestBody Category category)
	{
		return this.ok(this.repository.update(category));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseBody> delete(@PathVariable long id)
	{
		return this.ok(this.repository.deleteById(id));
	}

	@GetMapping
	public ResponseEntity<List<Category>> findAll()
	{
		return ResponseEntity.ok(this.repository.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable long id)
	{
		return ResponseEntity.ok(this.repository.getById(id));
	}
}

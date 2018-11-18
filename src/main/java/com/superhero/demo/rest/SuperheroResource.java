package com.superhero.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.superhero.demo.model.Superhero;
import com.superhero.demo.repository.SuperheroRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/superheros")
public class SuperheroResource {

	@Autowired
	private SuperheroRepository superheroRepository;

	@PostMapping("/add")
	@ApiOperation(value = "Add Superhero")
	public void createSuperhero(@RequestBody Superhero superhero) {
		superheroRepository.save(superhero);
	}

	@GetMapping("/list")
	@ApiOperation(value = "List all Superheros")
	public List<Superhero> retrieveAllSuperheros() {
		return superheroRepository.findAll();
	}

	@GetMapping("/show/{id}")
	@ApiOperation(value = "Find Superhero by id")
	public Superhero retrieveSuperhero(@PathVariable long id) {
		Optional<Superhero> superhero = superheroRepository.findById(id);

		if (!superhero.isPresent())
			throw new NotFoundException("id-" + id);

		return superhero.get();
	}

	@PutMapping("/update/{id}")
	@ApiOperation(value = "Update Superhero by id")
	public void updateSuperhero(@RequestBody Superhero superhero, @PathVariable long id) {
		superhero.setId(id);
		superheroRepository.save(superhero);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Delete Superhero by id")
	public void deleteSuperhero(@PathVariable long id) {

		superheroRepository.deleteById(id);
	}
}
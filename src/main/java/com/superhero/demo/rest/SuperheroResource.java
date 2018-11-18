package com.superhero.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
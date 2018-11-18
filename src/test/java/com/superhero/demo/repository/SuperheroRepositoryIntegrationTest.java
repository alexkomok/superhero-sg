package com.superhero.demo.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.superhero.demo.model.Superhero;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SuperheroRepositoryIntegrationTest {

	@Autowired
	private SuperheroRepository superheroRepository;

	@Test
	public void saveTest() {
		Superhero superhero = new Superhero("firstname", new Long(10), "lastname", null, "superheroname");
		superheroRepository.save(superhero);
		Optional<Superhero> found = superheroRepository.findById(5L);
		assertThat(found == null, is(false));
		assertThat(found.isPresent(), is(true));
		assertThat(superhero.getSuperheroname(), is(found.get().getSuperheroname()));
	}

	@Test
	public void findAllTest() {
		List<Superhero> superheros = superheroRepository.findAll();
		assertThat(superheros.isEmpty(), is(false));
	}

	@Test
	public void findByIdTest() {
		Optional<Superhero> superhero = superheroRepository.findById(1L);
		assertThat(superhero == null, is(false));
		assertThat(superhero.get() == null, is(false));
		assertThat(superhero.get().getSuperheroname().equals("batman"), is(true));
	}

}
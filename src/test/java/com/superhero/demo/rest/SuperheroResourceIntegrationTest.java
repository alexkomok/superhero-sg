package com.superhero.demo.rest;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.superhero.demo.model.Superhero;

@RunWith(SpringRunner.class)
@WebMvcTest(SuperheroResource.class)
public class SuperheroResourceIntegrationTest {

	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private SuperheroResource superheroResource;
    
    Logger logger = LoggerFactory.getLogger(SuperheroResourceIntegrationTest.class);
    Superhero superhero = new Superhero("firstname", new Long(1), "lastname", null, "superheroname");
    
	@Test
	public void createSuperheroTest() throws Exception {

		doNothing().when(superheroResource).createSuperhero(superhero);

		mvc.perform(post("/superheros/add", superhero.getId())
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(asJsonString(superhero)))
				.andExpect(status().isOk());

	}
    
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
 
}
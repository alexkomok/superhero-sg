package com.superhero.demo.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
	
	@Test
	public void retrieveAllSuperherosTest() throws Exception {

		List<Superhero> superheros = new ArrayList<Superhero>();
		superheros.add(superhero);

		when(superheroResource.retrieveAllSuperheros()).thenReturn(superheros);

		mvc.perform(get("/superheros/list").
				contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].firstname", is("firstname")))
				.andExpect(jsonPath("$[0].lastname", is("lastname")))
				.andExpect(jsonPath("$[0].superheroname", is("superheroname")));
		
		verify(superheroResource, times(1)).retrieveAllSuperheros();
		verifyNoMoreInteractions(superheroResource);  		

	}
    
    @Test
    public void retrieveSuperheroTest() throws Exception {
    	when(superheroResource.retrieveSuperhero(superhero.getId())).thenReturn(superhero);
    	
		mvc.perform(get("/superheros/show/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.firstname", is("firstname")))
				.andExpect(jsonPath("$.lastname", is("lastname")))
				.andExpect(jsonPath("$.superheroname", is("superheroname")));
    	
		verify(superheroResource, times(1)).retrieveSuperhero(superhero.getId());
		verifyNoMoreInteractions(superheroResource);    	

    }
    
	@Test
	public void updateSuperheroTest() throws Exception {

		doNothing().when(superheroResource).updateSuperhero(superhero, superhero.getId());

		mvc.perform(put("/superheros/update/{id}", superhero.getId())
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(asJsonString(superhero)))
				.andExpect(status().isOk());

	}    
	
    @Test
    public void deleteSuperheroTest() throws Exception {
    	
		when(superheroResource.retrieveSuperhero(superhero.getId())).thenReturn(superhero);
		doNothing().when(superheroResource).deleteSuperhero(superhero.getId());

		mvc.perform(get("/superheros/delete/{id}", superhero.getId())
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(asJsonString(superhero)))
				.andExpect(status().isOk());
		verify(superheroResource, times(1)).deleteSuperhero(superhero.getId());
		verifyNoMoreInteractions(superheroResource);

    } 	
    
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
 
}
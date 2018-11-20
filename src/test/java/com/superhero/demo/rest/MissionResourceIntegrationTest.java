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
import com.superhero.demo.model.Mission;

@RunWith(SpringRunner.class)
@WebMvcTest(MissionResource.class)
public class MissionResourceIntegrationTest {

	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private MissionResource missionResource;
    
    Logger logger = LoggerFactory.getLogger(MissionResourceIntegrationTest.class);
    Mission mission = new Mission();
    {
    	mission.setId(1L);
    	mission.setIsCompleted(false);
    	mission.setIsDeleted(false);
    	mission.setName("mission");
    }
    
	@Test
	public void createMissionTest() throws Exception {

		doNothing().when(missionResource).createMission(mission);

		mvc.perform(post("/missions/add", mission.getId())
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(asJsonString(mission)))
				.andExpect(status().isOk());

	}
	
	@Test
	public void retrieveAllMissionsTest() throws Exception {

		List<Mission> missions = new ArrayList<Mission>();
		missions.add(mission);

		when(missionResource.retrieveAllMissions()).thenReturn(missions);

		mvc.perform(get("/missions/list").
				contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].name", is("mission")))
				.andExpect(jsonPath("$[0].isCompleted", is(false)))
				.andExpect(jsonPath("$[0].isDeleted", is(false)));
		
		verify(missionResource, times(1)).retrieveAllMissions();
		verifyNoMoreInteractions(missionResource);  		

	}
    
    @Test
    public void retrieveMissionTest() throws Exception {
    	when(missionResource.retrieveMission(mission.getId())).thenReturn(mission);
    	
		mvc.perform(get("/missions/show/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.name", is("mission")))
				.andExpect(jsonPath("$.isCompleted", is(false)))
				.andExpect(jsonPath("$.isDeleted", is(false)));
    	
		verify(missionResource, times(1)).retrieveMission(mission.getId());
		verifyNoMoreInteractions(missionResource);    	

    }
    
	@Test
	public void updateMissionTest() throws Exception {

		doNothing().when(missionResource).updateMission(mission, mission.getId());

		mvc.perform(put("/missions/update/{id}", mission.getId())
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(asJsonString(mission)))
				.andExpect(status().isOk());

	}    
	
    @Test
    public void deleteMissionTest() throws Exception {
    	
		when(missionResource.retrieveMission(mission.getId())).thenReturn(mission);
		doNothing().when(missionResource).deleteMission(mission.getId());

		mvc.perform(get("/missions/delete/{id}", mission.getId())
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(asJsonString(mission)))
				.andExpect(status().isOk());
		verify(missionResource, times(1)).deleteMission(mission.getId());
		verifyNoMoreInteractions(missionResource);

    }	
    
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
 
}

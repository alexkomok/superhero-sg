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
    
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
 
}

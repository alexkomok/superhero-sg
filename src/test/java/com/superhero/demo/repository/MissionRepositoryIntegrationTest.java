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

import com.superhero.demo.model.Mission;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MissionRepositoryIntegrationTest {

	@Autowired
	private MissionRepository missionRepository;

	@Test
	public void saveTest() {
		Mission mission = new Mission();
		mission.setId(5L);
		mission.setIsCompleted(false);
		mission.setIsDeleted(false);
		mission.setName("mission");
		missionRepository.save(mission);
		Optional<Mission> found = missionRepository.findById(5L);
		assertThat(found == null, is(false));
		assertThat(found.isPresent(), is(true));
		assertThat(mission.getName(), is(found.get().getName()));
	}

	@Test
	public void findAllTest() {
		List<Mission> missions = missionRepository.findAll();
		assertThat(missions.isEmpty(), is(false));
	}

	@Test
	public void findByIdTest() {
		Optional<Mission> mission = missionRepository.findById(1L);
		assertThat(mission == null, is(false));
		assertThat(mission.get() == null, is(false));
		assertThat(mission.get().getName().equals("Rescue world"), is(true));
	}

	@Test
	public void deleteByIdTest() {
		missionRepository.deleteById(1L);
		Optional<Mission> mission = missionRepository.findById(1L);
		assertThat(mission == null, is(false));
		assertThat(mission.isPresent(), is(false));
	}

}

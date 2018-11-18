package com.superhero.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.superhero.demo.model.Mission;
import com.superhero.demo.repository.MissionRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/missions")
public class MissionResource {

	@Autowired
	private MissionRepository missionRepository;

	@PostMapping("/add")
	@ApiOperation(value = "Add Mission")
	public void createMission(@RequestBody Mission superhero) {
		missionRepository.save(superhero);
	}

	@GetMapping("/list")
	@ApiOperation(value = "List all Missions")
	public List<Mission> retrieveAllMissions() {
		return missionRepository.findAll();
	}

	@GetMapping("/show/{id}")
	@ApiOperation(value = "Find Mission by id")
	public Mission retrieveMission(@PathVariable long id) {
		Optional<Mission> superhero = missionRepository.findById(id);

		if (!superhero.isPresent())
			throw new NotFoundException("id-" + id);

		return superhero.get();
	}

}

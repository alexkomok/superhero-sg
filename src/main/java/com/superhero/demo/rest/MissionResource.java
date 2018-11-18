package com.superhero.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
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

}

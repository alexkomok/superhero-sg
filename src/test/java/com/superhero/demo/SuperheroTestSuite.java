package com.superhero.demo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.superhero.demo.repository.MissionRepositoryIntegrationTest;
import com.superhero.demo.repository.SuperheroRepositoryIntegrationTest;
import com.superhero.demo.rest.MissionResourceIntegrationTest;
import com.superhero.demo.rest.SuperheroResourceIntegrationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	SuperheroSgApplicationTests.class,
	MissionRepositoryIntegrationTest.class,
	SuperheroRepositoryIntegrationTest.class,
	MissionResourceIntegrationTest.class,
	SuperheroResourceIntegrationTest.class
})
public class SuperheroTestSuite {

}

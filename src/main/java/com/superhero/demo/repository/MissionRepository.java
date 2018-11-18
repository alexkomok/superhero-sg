package com.superhero.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.superhero.demo.model.Mission;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long>{

}	
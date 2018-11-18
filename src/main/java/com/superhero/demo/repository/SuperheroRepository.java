package com.superhero.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.superhero.demo.model.Superhero;

@Repository
public interface SuperheroRepository extends JpaRepository<Superhero, Long>{

}	
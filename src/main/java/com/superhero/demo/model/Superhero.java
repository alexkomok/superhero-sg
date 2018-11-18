package com.superhero.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the superhero database table.
 * 
 */
@Entity
public class Superhero {

	@JsonCreator
	public Superhero(@JsonProperty("firstname") String firstname, @JsonProperty("id") Long id,
			@JsonProperty("lastname") String lastname, @JsonProperty("missions") List<Mission> missions,
			@JsonProperty("superheroname") String superheroname) {
		super();
		this.firstname = firstname;
		this.id = id;
		this.lastname = lastname;
		this.missions = missions;
		this.superheroname = superheroname;
	}

	@JsonCreator
	public Superhero() {
	};

	@Column(name = "firstname")
	private String firstname;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "lastname")
	private String lastname;

	@ManyToMany(cascade = CascadeType.PERSIST, targetEntity = Mission.class, fetch = FetchType.EAGER)
	@JoinTable(name = "superhero_mission", joinColumns = @JoinColumn(name = "superhero_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "mission_id", referencedColumnName = "id"))
	@JsonManagedReference
	private List<Mission> missions;

	@Column(name = "superheroname")
	private String superheroname;

	public String getFirstname() {
		return firstname;
	}

	public Long getId() {
		return id;
	}

	public String getLastname() {
		return lastname;
	}

	public String getSuperheroname() {
		return superheroname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setSuperheroname(String superheroname) {
		this.superheroname = superheroname;
	}

	public List<Mission> getMissions() {
		return missions;
	}

	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}

}
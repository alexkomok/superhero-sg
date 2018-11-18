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

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * The persistent class for the mission database table.
 * 
 */
@Entity
public class Mission {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "is_completed")
	private Boolean IsCompleted;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Column(name = "name")
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,	CascadeType.PERSIST }, targetEntity = Superhero.class)
	@JoinTable(name = "superhero_mission", joinColumns = @JoinColumn(name = "mission_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "superhero_id", referencedColumnName = "id"))
	@JsonBackReference
	private List<Superhero> superheros;

	public Long getId() {
		return id;
	}

	public Boolean getIsCompleted() {
		return IsCompleted;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIsCompleted(Boolean isCompleted) {
		IsCompleted = isCompleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Superhero> getSuperheros() {
		return superheros;
	}

	public void setSuperheros(List<Superhero> superheros) {
		this.superheros = superheros;
	}

}
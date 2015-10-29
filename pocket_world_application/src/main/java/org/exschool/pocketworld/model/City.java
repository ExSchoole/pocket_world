package org.exschool.pocketworld.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class City {
	@Id
	@GeneratedValue
	@Column(name="idPk")
	private int id;
	private String name;
	@OneToMany(targetEntity=Building.class,mappedBy="city",
			cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Building> buildings;
	@OneToMany(targetEntity=Resource.class,mappedBy="city",
			cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Resource> resources;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public List<Building> getBildings() {
		return buildings;
	}
	public void setBildings(List<Building> buildings) {
		this.buildings = buildings;
	}
	
	public List<Resource> getResources() {
		return resources;
	}
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
}

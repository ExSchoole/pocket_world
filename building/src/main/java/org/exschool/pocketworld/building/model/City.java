package org.exschool.pocketworld.building.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class City {
	@Id
	@GeneratedValue
	@Column(name="idPk")
	private int id;
	
	private String name;
	@OneToMany(targetEntity=Building.class,mappedBy="city",
			cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Building> buildings;
	
	
	
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
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
}

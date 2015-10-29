package org.exschool.pocketworld.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Building {
	@ManyToOne
	@JoinColumn(name="city")
	private City city;
	@Id
	private BuildingsType type;
	private int level;
	@OneToMany(targetEntity=BuildingResource.class,mappedBy="building",
			cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<BuildingResource> bildingResouces;
	@OneToMany(targetEntity=Production.class,mappedBy="building",
			cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Production> productions;
	
	
	
	public BuildingsType getBildingType() {
		return type;
	}
	public void setBildingType(BuildingsType bildingType) {
		this.type = bildingType;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	public List<BuildingResource> getBildingResouces() {
		return bildingResouces;
	}
	public void setBildingResouces(List<BuildingResource> bildingResouces) {
		this.bildingResouces = bildingResouces;
	}
	
	
	public List<Production> getProductions() {
		return productions;
	}
	public void setProductions(List<Production> productions) {
		this.productions = productions;
	}
	
	
	
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	
}

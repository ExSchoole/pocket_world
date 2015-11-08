package org.exschool.pocketworld.building.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.exschool.pocketworld.resource.model.BuildingResource;
import org.exschool.pocketworld.resource.model.Production;
import org.exschool.pocketworld.resource.model.Time;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Table;

@Entity
public class Building {
	@ManyToOne
	@JoinColumn(name="city")
	private City city;
	
	@Id
	private BuildingType buildingType;
	
	
	private int level;
	
	@Embedded
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="building_resources", joinColumns=@JoinColumn(name="type"))

	private List<BuildingResource> bildingResouces;
	
	@Embedded
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="building_production", joinColumns=@JoinColumn(name="type"))
	
	private List<Production> productions;
	
	
	
	//private Time time;
	
	int position;
	
	
	
	/*public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}*/
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public BuildingType getBuildingType() {
		return buildingType;
	}
	public void setBuildingType(BuildingType buildingType) {
		this.buildingType = buildingType;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	/*public List<BuildingResource> getBildingResouces() {
		return bildingResouces;
	}
	public void setBildingResouces(List<BuildingResource> bildingResouces) {
		this.bildingResouces = bildingResouces;
	}*/
	
	
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

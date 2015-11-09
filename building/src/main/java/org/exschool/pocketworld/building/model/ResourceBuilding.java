package org.exschool.pocketworld.building.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;


@Entity
@Table(name="Resource_building") 
@SecondaryTable(name="Time",pkJoinColumns=@PrimaryKeyJoinColumn(name="id"))
public class ResourceBuilding {
	
	@ManyToOne
	@JoinColumn(name="city")
	private City city;
	
	@Id
	@GeneratedValue
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "building_type")
	private ResourceBuildingType resourceBuildingType;
	
	
	
	private int level;
	

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="building_resources", joinColumns=@JoinColumn(name="id"))

	private List<BuildingResource> bildingResouces;
	

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="building_production", joinColumns=@JoinColumn(name="id"))
	
	private List<Production> productions;
	
	@Embedded
	@AttributeOverrides({
        @AttributeOverride(name="buildingType", column=@Column(table="Time")),
        @AttributeOverride(name="level", column=@Column(name="CITY", table="Time")),
        @AttributeOverride(name="time", column=@Column(name="STATE", table="Time")),
        
	})
	private Time time;
	
	int position;
	
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ResourceBuildingType getResourceBuildingType() {
		return resourceBuildingType;
	}

	public void setResourceBuildingType(ResourceBuildingType resourceBuildingType) {
		this.resourceBuildingType = resourceBuildingType;
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

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}


}

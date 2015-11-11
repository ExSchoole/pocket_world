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
	@CollectionTable(name="building_production", joinColumns=@JoinColumn(name="id"))
	
	private List<Production> productions;

	
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

	public List<Production> getProductions() {
		return productions;
	}

	public void setProductions(List<Production> productions) {
		this.productions = productions;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}


}

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
@Table(name="Building") 
@SecondaryTable(name="Time",pkJoinColumns=@PrimaryKeyJoinColumn(name="id"))
public class Building {
	@ManyToOne
	@JoinColumn(name="city")
	private City city;
	
	@Id
	@GeneratedValue
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "building_type")
	private BuildingType buildingType;
	
	
	private int level;

	int position;
	
	
	
	
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
	
	
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	
}

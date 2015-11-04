package org.exschool.pocketworld.resource.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.exschool.pocketworld.building.model.BuildingType;

@Embeddable
public class Time {
	
	private int level;
	private int time;
	@Enumerated(EnumType.STRING)
	@Column(name = "building_type")
	private BuildingType buildingType;
	
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public BuildingType getBuildingsType() {
		return buildingType;
	}
	public void setBuildingsType(BuildingType buildingType) {
		this.buildingType = buildingType;
	}
	
	
	
	
}

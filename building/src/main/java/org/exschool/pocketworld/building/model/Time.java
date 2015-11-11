package org.exschool.pocketworld.building.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Time {
	
	private BuildingType buildingType;

	private int level;
	@Id
	private int time;

	
	
	
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

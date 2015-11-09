package org.exschool.pocketworld.building.model;

import javax.persistence.Embeddable;

@Embeddable
public class Time {
	
	private BuildingType buildingType;

	private int level;
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

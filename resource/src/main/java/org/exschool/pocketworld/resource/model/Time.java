package org.exschool.pocketworld.resource.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.exschool.pocketworld.building.model.BuildingType;

@Entity
public class Time {
	

	private int nextLevel;
	private int time;
	@Id
	private BuildingType buildingType;
	
	
	public int getLevel() {
		return nextLevel;
	}
	public void setLevel(int nextLevel) {
		this.nextLevel = nextLevel;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	/*public BuildingType getBuildingsType() {
		return buildingType;
	}
	public void setBuildingsType(BuildingType buildingType) {
		this.buildingType = buildingType;
	}*/
	
	
	
	
}

package org.exschool.pocketworld.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Time {
	@Id
	@Column(name="level")
	private int level;
	private int time;
	private BuildingsType buildingsType;
	
	
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
	public BuildingsType getBuildingsType() {
		return buildingsType;
	}
	public void setBuildingsType(BuildingsType buildingsType) {
		this.buildingsType = buildingsType;
	}
	
	
	
	
}

package org.exschool.pocketworld.model;


import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;

@Entity
public class BuildingResource {
	@ManyToOne
	@JoinColumn(name="building")
	private Building building;
	protected ResourceType tresourceType;
	protected int amount;
	private int level;
	@Id
	private BuildingsType buildingType;
	
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public BuildingsType getBildingType() {
		return buildingType;
	}
	public void setBildingType(BuildingsType buildingType) {
		this.buildingType = buildingType;
	}
	
	
	public Building getBilding() {
		return building;
	}
	public void setBilding(Building building) {
		this.building = building;
	}

	
	
	
}

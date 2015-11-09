package org.exschool.pocketworld.building.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.exschool.pocketworld.resource.model.ResourceType;

@Embeddable
public class BuildingResource {

	protected ResourceType resourceType;
	protected int amount;
	private int level;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "building_type")
	private BuildingType buildingType;
	
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public BuildingType getBildingType() {
		return buildingType;
	}
	public void setBildingType(BuildingType buildingType) {
		this.buildingType = buildingType;
	}
	
	
	

	
	
	
}
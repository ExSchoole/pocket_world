package org.exschool.pocketworld.resource.model;


import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.exschool.pocketworld.building.model.BuildingType;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class BuildingResource {

	protected ResourceType resourceType;
	protected int amount;
	private int level;
	
	//private BuildingType type;
	
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	/*public BuildingType getBildingType() {
		return type;
	}
	public void setBildingType(BuildingType type) {
		this.type = type;
	}*/
	
	
	

	
	
	
}

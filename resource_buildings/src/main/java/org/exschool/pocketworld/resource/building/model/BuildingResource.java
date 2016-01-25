package org.exschool.pocketworld.resource.building.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "ResourceBuildingInformation")
public class BuildingResource {

	@EmbeddedId
	private BuildingResourceId buildingResourceId;
	private int amount;

	public BuildingResource(){
		
	}
	
	public BuildingResource(BuildingResourceId buildingResourceId, int amount){
		this.buildingResourceId = buildingResourceId;
		this.amount = amount;
	}
	
	public BuildingResourceId getBuildingResourceId() {
		return buildingResourceId;
	}

	public void setBuildingResourceId(BuildingResourceId buildingResourceId) {
		this.buildingResourceId = buildingResourceId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}

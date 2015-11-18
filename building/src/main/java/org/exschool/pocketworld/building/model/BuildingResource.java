package org.exschool.pocketworld.building.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Entity
public class BuildingResource {

	@EmbeddedId
	private BuildingResourceId buildingResourceId;
	private int amount;

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

package org.exschool.pocketworld.building.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Entity
public class BuildingResource {

	@EmbeddedId
	private BuildingResourcePK buildingResourcePK;
	private int amount;

	public BuildingResourcePK getBuildingResourcePK() {
		return buildingResourcePK;
	}

	public void setBuildingResourcePK(BuildingResourcePK buildingResourcePK) {
		this.buildingResourcePK = buildingResourcePK;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}

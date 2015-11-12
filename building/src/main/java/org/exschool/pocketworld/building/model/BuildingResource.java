package org.exschool.pocketworld.building.model;

import javax.persistence.*;

import org.exschool.pocketworld.resource.model.ResourceType;

@Entity
public class BuildingResource {

	@EmbeddedId
	BuildingResourcePK buildingResourcePK;
	protected int amount;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}

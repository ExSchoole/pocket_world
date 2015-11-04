package org.exschool.pocketworld.building.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.exschool.pocketworld.resource.model.ProductionBalance;
import org.exschool.pocketworld.resource.model.Time;

@Embeddable
public class Building {
	
	@Enumerated(EnumType.STRING)
	@Column(name = "building_type")
	private BuildingType buildingType;
	private int level;
	
	@Embedded
	@Column(name = "building_balance")
	private BuildingResource bildingResouces;
	
	@Embedded
	@Column(name = "production_balance")
	private ProductionBalance productionBalance;
	
	@Embedded
	@Column(name = "time")
	Time time;
	
	int position;
	
	
	public BuildingType getBildingType() {
		return buildingType;
	}
	public void setBildingType(BuildingType bildingType) {
		this.buildingType = bildingType;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	

	public ProductionBalance getProductionBalance() {
		return productionBalance;
	}
	public void setProductionBalance(ProductionBalance productionBalance) {
		this.productionBalance = productionBalance;
	}
	public BuildingResource getBildingResouces() {
		return bildingResouces;
	}
	public void setBildingResouces(BuildingResource bildingResouces) {
		this.bildingResouces = bildingResouces;
	}
	
}

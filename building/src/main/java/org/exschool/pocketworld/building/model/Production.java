package org.exschool.pocketworld.building.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Production {
	@Enumerated(EnumType.STRING)
	@Column(name = "production_type")
	private ProductionType productionType;
	
	
	private int amount;
	
	private int level;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "building_type")
	private BuildingType buildingType;
	
	public ProductionType getProductionType() {
		return productionType;
	}
	public void setProductionType(ProductionType productionType) {
		this.productionType = productionType;
	}
	public int getCount() {
		return amount;
	}
	public void setCount(int amount) {
		this.amount = amount;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
	
	
}

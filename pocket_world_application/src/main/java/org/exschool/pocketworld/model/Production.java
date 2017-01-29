package org.exschool.pocketworld.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Production {
	@ManyToOne
	@JoinColumn(name="building")
	private Building building;
	@Id
	private ProductionType productionType;
	private int count;
	private int level;
	
	
	public ProductionType getProductionType() {
		return productionType;
	}
	public void setProductionType(ProductionType productionType) {
		this.productionType = productionType;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	public Building getBilding() {
		return building;
	}
	public void setBilding(Building building) {
		this.building = building;
	}
	
	
}

package org.exschool.pocketworld.building.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
/**
 * Created by manoylo on 20.11.15.
 *
 */
@Embeddable
public class ProductionId implements Serializable{
	
	@Enumerated(EnumType.STRING)
	@Column(name = "production_type")
	private ProductionType productionType;
	
	private int level;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "building_type")
	private ResourceBuildingType resourceBuildingType;
	
	public ProductionType getProductionType() {
		return productionType;
	}
	public void setProductionType(ProductionType productionType) {
		this.productionType = productionType;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
}

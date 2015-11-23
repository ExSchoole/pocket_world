package org.exschool.pocketworld.building.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
/**
 * Created by manoylo on 20.11.15.
 *
 */
@Entity
public class Production {
	@EmbeddedId
	private ProductionId  productionId ;
	
	private int amount;
	
	public ProductionId getProductionId() {
		return productionId;
	}
	public void setProductionId(ProductionId productionId) {
		this.productionId = productionId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}

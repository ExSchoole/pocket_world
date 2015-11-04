package org.exschool.pocketworld.resource.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

@Embeddable
public class ProductionBalance {
	
	@ElementCollection(targetClass=Production.class,fetch = FetchType.EAGER)
	@CollectionTable(name="building_production", joinColumns=@JoinColumn(name="buildingType"))
	List<Production> product_resources = new ArrayList<>();

	public List<Production> getResources() {
		return product_resources;
	}

	public void setResources(List<Production> product_resources) {
		this.product_resources = product_resources;
	}

}

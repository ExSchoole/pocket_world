package org.exschool.pocketworld.resource.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Resource {
	@Enumerated(EnumType.STRING)
	@Column(name = "resource_type")
	private ResourceType resourceType;

	@Column(name = "amount")
	private int amount;

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int count) {
		this.amount = count;
	}
	public Resource() {}
	
	public Resource(ResourceType resourceType, int amount) {
		this.resourceType = resourceType;
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Resource [resourceType=" + resourceType + ", amount=" + amount
				+ "]";
	}

}

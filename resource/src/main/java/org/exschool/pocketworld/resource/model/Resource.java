package org.exschool.pocketworld.resource.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Resource {

	public Resource() {}

	public Resource(ResourceType resourceType, int amount) {
		this.resourceType = resourceType;
		this.amount = amount;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "resource_type")
	protected ResourceType resourceType;

	@Column(name = "amount")
	protected int amount;

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

	@Override
	public String toString() {
		return "Resource [resourceType=" + resourceType + ", amount=" + amount
				+ "]";
	}

}

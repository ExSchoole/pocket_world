package org.exschool.pocketworld.resource.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Resource")
public class Resource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "resource_type")
	private ResourceType resourceType;
	
	private Long playerId;
	
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
	
	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = id != null ? id.hashCode() : 0;
		result = prime * result + amount;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((playerId == null) ? 0 : playerId.hashCode());
		result = prime * result + ((resourceType == null) ? 0 : resourceType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)return true;
		if (obj == null)return false;
		
		
		Resource other = (Resource) obj;
		
		if (amount != other.amount)return false;
		if (id != null ? !id.equals(other.id) : other.id != null) return false;
		if (playerId != null ? !playerId.equals(other.playerId) : other.playerId != null) return false;
		if (resourceType != other.resourceType)return false;
		
		return true;
	}

	
	
	

}

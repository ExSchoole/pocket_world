package org.exschool.pocketworld.resource.building.model;

import org.exschool.pocketworld.resource.model.ResourceType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

/**
 * Created by manoylo on 20.11.15.
 */
@Embeddable
public class ProductionId implements Serializable {

	@Enumerated(EnumType.STRING)
    @Column(name = "resource_type")
    private ResourceType resourceType;

    private Integer level;

    public ProductionId(){
    	
    }
    
    public ProductionId(ResourceType resourceType, int level){
    	this.resourceType = resourceType;
    	this.level = level;
    }
    
    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((resourceType == null) ? 0 : resourceType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductionId other = (ProductionId) obj;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (resourceType != other.resourceType)
			return false;
		return true;
	}
}

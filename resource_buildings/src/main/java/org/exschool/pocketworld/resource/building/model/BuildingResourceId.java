package org.exschool.pocketworld.resource.building.model;

import org.exschool.pocketworld.resource.model.ResourceType;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import java.io.Serializable;


@Embeddable
public class BuildingResourceId implements Serializable {
	@Enumerated(EnumType.STRING)
	private ResourceType buildingType;
	@Enumerated(EnumType.STRING)
    private ResourceType resourceType;
    private Integer level;

    public ResourceType getBuildingType() {
		return buildingType;
	}
    
    public ResourceType getResourceType() {
        return resourceType;
    }

    public Integer getLevel() {
        return level;
    }

    public BuildingResourceId(){}
    public BuildingResourceId(ResourceType buildingType, ResourceType resourceType, Integer level) {
    	this.buildingType = buildingType;
        this.resourceType = resourceType;
        this.level = level;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buildingType == null) ? 0 : buildingType.hashCode());
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
		BuildingResourceId other = (BuildingResourceId) obj;
		if (buildingType != other.buildingType)
			return false;
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

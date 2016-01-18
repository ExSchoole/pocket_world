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
}

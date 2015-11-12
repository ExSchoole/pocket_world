package org.exschool.pocketworld.building.model;

import org.exschool.pocketworld.resource.model.ResourceType;

import javax.persistence.Embeddable;
import javax.persistence.Id;

/**
 * Created by skandy on 12.11.15.
 */
@Embeddable
public class BuildingResourcePK {
    private ResourceType resourceType;
    private Integer level;
    public BuildingResourcePK(){}
    public BuildingResourcePK(ResourceType resourceType, Integer level) {
        this.resourceType = resourceType;
        this.level = level;
    }
}

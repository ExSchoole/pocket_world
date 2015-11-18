package org.exschool.pocketworld.building.model;

import org.exschool.pocketworld.resource.model.ResourceType;

import javax.persistence.Embeddable;
import java.io.Serializable;


/**
 * Created by skandy on 12.11.15.
 */
@Embeddable
public class BuildingResourcePK implements Serializable {
    private ResourceType resourceType;
    private Integer level;

    public ResourceType getResourceType() {
        return resourceType;
    }

    public Integer getLevel() {
        return level;
    }

    public BuildingResourcePK(){}
    public BuildingResourcePK(ResourceType resourceType, Integer level) {
        this.resourceType = resourceType;
        this.level = level;
    }
}

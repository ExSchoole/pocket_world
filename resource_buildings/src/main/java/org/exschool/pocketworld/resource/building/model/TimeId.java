package org.exschool.pocketworld.resource.building.model;

import org.exschool.pocketworld.resource.model.ResourceType;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import java.io.Serializable;

/**
 * Created by skandy on 12.11.15.
 */
@Embeddable
public class TimeId implements Serializable {
	@Enumerated(EnumType.STRING)
    private ResourceType buildingType;

    private Integer level;

    public TimeId() {
    }

    public TimeId(ResourceType buildingType, Integer level) {
        this.buildingType = buildingType;
        this.level = level;
    }

    public ResourceType getBuildingType() {
        return buildingType;
    }

    public Integer getLevel() {
        return level;
    }
}

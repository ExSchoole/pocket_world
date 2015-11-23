package org.exschool.pocketworld.building.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by skandy on 12.11.15.
 */
@Embeddable
public class TimeId implements Serializable {
    private ResourceBuildingType buildingType;

    private Integer level;

    public TimeId() {
    }

    public TimeId(ResourceBuildingType buildingType, Integer level) {
        this.buildingType = buildingType;
        this.level = level;
    }

    public ResourceBuildingType getBuildingType() {
        return buildingType;
    }

    public Integer getLevel() {
        return level;
    }
}

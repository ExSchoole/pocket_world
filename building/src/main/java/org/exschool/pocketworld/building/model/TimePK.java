package org.exschool.pocketworld.building.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by skandy on 12.11.15.
 */
@Embeddable
public class TimePK implements Serializable {
    private BuildingType buildingType;

    private Integer level;

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public Integer getLevel() {
        return level;
    }

    public TimePK() {}
    public TimePK(BuildingType buildingType, Integer level) {
        this.buildingType=buildingType;
        this.level= level;
    }


}

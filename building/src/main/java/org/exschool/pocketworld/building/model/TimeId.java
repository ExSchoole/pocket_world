package org.exschool.pocketworld.building.model;

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
    private BuildingType buildingType;

    private Integer level;
     
    public BuildingType getBuildingType() {
        return buildingType;
    }

    public Integer getLevel() {
        return level;
    }

    public TimeId() {}
    public TimeId(BuildingType buildingType, Integer level) {
        this.buildingType=buildingType;
        this.level= level;
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buildingType == null) ? 0 : buildingType.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
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
		TimeId other = (TimeId) obj;
		if (buildingType != other.buildingType)
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		return true;
	}
	
}

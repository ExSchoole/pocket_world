package org.exschool.pocketworld.resource.building.model;


import org.exschool.pocketworld.resource.model.ResourceType;

import javax.persistence.*;

/**
 * Created by manoylo on 20.11.15.
 */

@Entity
@Table(name = "Resource_building")
public class ResourceBuilding {

    private int position;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "building_type")
    private ResourceType resourceType;
    private int level;
    private Long cityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceBuilding building = (ResourceBuilding) o;

        if (level != building.level) return false;
        if (position != building.position) return false;
        if (resourceType != building.resourceType) return false;
        if (cityId != null ? !cityId.equals(building.cityId) : building.cityId != null) return false;
        if (id != null ? !id.equals(building.id) : building.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (resourceType != null ? resourceType.hashCode() : 0);
        result = 31 * result + level;
        result = 31 * result + position;
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        return result;
    }

}

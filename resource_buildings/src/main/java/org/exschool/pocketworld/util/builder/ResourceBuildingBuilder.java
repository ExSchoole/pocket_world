package org.exschool.pocketworld.util.builder;


import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.model.ResourceType;

/**
 * Created by manoylo on 18.11.15.
 */
public final class ResourceBuildingBuilder {


    private ResourceType resourceBuildingType;
    private int level;
    private int position;
    private Long cityId;

    public static ResourceBuildingBuilder builder() {
        return new ResourceBuildingBuilder();
    }

    private ResourceBuildingBuilder() {
    }

    public ResourceBuildingBuilder buildingType(ResourceType resourceBuildingType) {
        this.resourceBuildingType = resourceBuildingType;
        return this;
    }

    public ResourceBuildingBuilder level(int level) {
        this.level = level;
        return this;
    }

    public ResourceBuildingBuilder position(int position) {
        this.position = position;
        return this;
    }

    public ResourceBuildingBuilder cityId(Long cityId) {
        this.cityId = cityId;
        return this;

    }

    public ResourceBuilding build() {
        ResourceBuilding building = new ResourceBuilding();

        building.setResourceType(resourceBuildingType);
        building.setLevel(level);
        building.setPosition(position);
        building.setCityId(cityId);
        return building;
    }


}

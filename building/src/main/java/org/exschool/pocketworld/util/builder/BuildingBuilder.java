package org.exschool.pocketworld.util.builder;

import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.building.model.BuildingType;

/**
 * Created by skandy on 18.11.15.
 */
public class BuildingBuilder {
    private Long buildingId;
    private BuildingType buildingType;
    private int level;
    private int position;
    private Long cityId;

    public static BuildingBuilder builder() {
        return new BuildingBuilder();
    }

    private BuildingBuilder() {
    }

    public BuildingBuilder buildingId(Long buildingId) {
        this.buildingId = buildingId;
        return this;

    }

    public BuildingBuilder buildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
        return this;
    }

    public BuildingBuilder level(int level) {
        this.level = level;
        return this;
    }

    public BuildingBuilder position(int position) {
        this.position = position;
        return this;
    }

    public BuildingBuilder cityId(Long cityId) {
        this.cityId = cityId;
        return this;

    }

    public Building build() {
        Building building = new Building();
        building.setId(buildingId);
        building.setBuildingType(buildingType);
        building.setLevel(level);
        building.setPosition(position);
        building.setCityId(cityId);
        return building;
    }
}

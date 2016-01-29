package org.exschool.pocketworld.building.service;

import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.building.model.BuildingType;

import java.util.List;

/**
 * Created by skandy on 12.11.15.
 */
public interface BuildingService {
    List<Building> allBuildings();

    Building get(long id);

    Building save(Building entity);

    Building getAtPosition(Long cityId, Integer position);

    Boolean isBuildingExist(Long cityId, Integer position);

    public Long createBuilding(Long cityId, BuildingType buildingType, int position, int level);
}


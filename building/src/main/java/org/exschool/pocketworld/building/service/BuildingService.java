package org.exschool.pocketworld.building.service;

import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.building.model.BuildingResourceId;
import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.building.model.TimeId;
import org.exschool.pocketworld.resource.model.ResourceType;

import java.util.List;
import java.util.Map;

/**
 * Created by skandy on 12.11.15.
 */
public interface BuildingService {
    List<Building> allBuildings();

    Building get(long id);

    Map<BuildingResourceId, Integer> getResourceBuildingInfo();

    Map<TimeId, Integer> getTimeInfo();

    void saveAllInformation();

    Building save(Building entity);

    List<Building> getBuildingsByCityId(Long cityId);

    int getTimeByBuildingTypeLevel(BuildingType buildingType, int level);

    int getResourceByBuildingTypeResourceTypeLevel(BuildingType buildingType, ResourceType resourceType, int level);

    Building getAtPosition(Long cityId, Integer position);

    Boolean isBuildingExist(Long cityId, Integer position);

    Long createBuilding(Long cityId, BuildingType buildingType, int position, int level);
    
    void increaseLevel(Long cityId, Integer[] ids);
}


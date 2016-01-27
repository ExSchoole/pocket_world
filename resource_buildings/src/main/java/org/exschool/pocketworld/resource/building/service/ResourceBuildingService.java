package org.exschool.pocketworld.resource.building.service;

import java.util.List;
import java.util.Map;

import org.exschool.pocketworld.resource.building.model.BuildingResourceId;
import org.exschool.pocketworld.resource.building.model.ProductionId;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.building.model.TimeId;
import org.exschool.pocketworld.resource.model.ResourceType;

/**
 * Created by manoylo on 20.11.15.
 */
public interface ResourceBuildingService {
    List<ResourceBuilding> allBuildings();
    ResourceBuilding get (long id);
    Map<BuildingResourceId, Integer> getRESOURCE_BUILDINGS_INFO();
	Map<TimeId, Integer> getTIME_RESOURCE_BUILDINGS_INFO();
	Map<ProductionId, Integer> getPRODUCTION_RESOURCE_BUILDINGS_INFO();
    ResourceBuilding save(ResourceBuilding entity);
    List<ResourceBuilding> allCityResources(Long id);
    int getTimeByBuildingTypeLevel(ResourceType buildingType, int level);
    int getProductionByBuildingTypeLevel(ResourceType buildingType, int level);
    int getResourcesByBuildingTypeLevel(ResourceType buildingType,ResourceType resourceType, int level);
    void saveAllInformation();
}


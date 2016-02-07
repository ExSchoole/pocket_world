package org.exschool.pocketworld.resource.building.service;

import org.exschool.pocketworld.resource.building.model.BuildingResourceId;
import org.exschool.pocketworld.resource.building.model.ProductionId;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.building.model.TimeId;
import org.exschool.pocketworld.resource.model.ResourceType;

import java.util.List;
import java.util.Map;

/**
 * Created by manoylo on 20.11.15.
 */
public interface ResourceBuildingService {
    List<ResourceBuilding> allBuildings();

    ResourceBuilding get(long id);

    Map<BuildingResourceId, Integer> getRESOURCE_BUILDINGS_INFO();

    Map<TimeId, Integer> getTIME_RESOURCE_BUILDINGS_INFO();

    Map<ProductionId, Integer> getPRODUCTION_RESOURCE_BUILDINGS_INFO();

    ResourceBuilding save(ResourceBuilding entity);

    ResourceBuilding getAtPosition(Long cityId, Integer position);

    boolean isResBuildingExist(Long cityId, int position);

    Long createResourceBuilding(Long cityId, ResourceType resourceType, int position, int level);

    List<ResourceBuilding> allCityResources(Long id);

    int getTimeByBuildingTypeLevel(ResourceType buildingType, int level);

    int getProductionByBuildingTypeLevel(ResourceType buildingType, int level);

    int getResourcesByBuildingTypeLevel(ResourceType buildingType, ResourceType resourceType, int level);

    void saveAllInformation();
    
    void increaseLevel(Long cityId, List<Long> ids);
}


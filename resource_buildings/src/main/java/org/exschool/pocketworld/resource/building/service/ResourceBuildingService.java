package org.exschool.pocketworld.resource.building.service;

import org.exschool.pocketworld.resource.building.model.ResourceBuilding;

import java.util.List;

/**
 * Created by manoylo on 20.11.15.
 */
public interface ResourceBuildingService {
    List<ResourceBuilding> allBuildings();
    ResourceBuilding get (long id);
    ResourceBuilding save(ResourceBuilding entity);
    List<ResourceBuilding> allCityBuildings(Long id);
    ResourceBuilding getAtPosition(Long cityId,Integer position);
}


package org.exschool.pocketworld.building.service;

import org.exschool.pocketworld.building.model.Building;

import java.util.List;

/**
 * Created by skandy on 12.11.15.
 */
public interface BuildingService {
    List<Building> allBuildings();
    Building get (long id);
    Building save(Building entity);
    List<Building> getBuildingsByCityId(Long cityId);
}


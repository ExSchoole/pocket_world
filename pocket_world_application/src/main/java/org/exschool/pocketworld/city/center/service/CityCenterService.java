package org.exschool.pocketworld.city.center.service;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

import org.exschool.pocketworld.building.BuildingDto;
import org.exschool.pocketworld.city.center.dto.CityCenterDto;

public interface CityCenterService {
    CityCenterDto cityCenterInfo();

    boolean addBuilding(BuildingDto newBuilding);

    Collection<String> availableForBuildBuildingTypes(Set<String> builtBuildingTypes);
}

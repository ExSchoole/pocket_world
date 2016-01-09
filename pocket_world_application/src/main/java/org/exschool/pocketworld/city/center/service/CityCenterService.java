package org.exschool.pocketworld.city.center.service;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

import org.exschool.pocketworld.building.BuildingDto;
import org.exschool.pocketworld.city.center.dto.CityCenterDto;

public interface CityCenterService {
    CityCenterDto cityCenterInfo(String playerName);

    boolean addBuilding(String playerName,String type, int level, int position);

    Collection<String> availableForBuildBuildingTypes(Set<String> builtBuildingTypes);
}

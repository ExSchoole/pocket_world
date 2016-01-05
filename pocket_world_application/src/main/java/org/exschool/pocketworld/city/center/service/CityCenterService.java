package org.exschool.pocketworld.city.center.service;

import java.util.Collection;
import java.util.Set;

import org.exschool.pocketworld.building.BuildingInterim;
import org.exschool.pocketworld.city.center.dto.CityCenterDto;

public interface CityCenterService {
    CityCenterDto cityCenterInfo();

    boolean addBuilding(Long cityId,int position, BuildingInterim newBuilding);

    Collection<String> availableForBuildBuildingTypes(Set<String> builtBuildingTypes);
}

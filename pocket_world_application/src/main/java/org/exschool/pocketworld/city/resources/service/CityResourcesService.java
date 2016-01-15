package org.exschool.pocketworld.city.resources.service;

import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.dto.PositionOfBuilding;

public interface CityResourcesService {
    CityResourcesDto cityResourcesInfo();

    boolean  createResourceBuilding(PositionOfBuilding positionOfBuilding);
}

package org.exschool.pocketworld.city.resources.service;

import java.util.List;

import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.dto.PositionOfBuilding;

public interface CityResourcesService {
    CityResourcesDto cityResourcesInfo();

    boolean createResourceBuilding(PositionOfBuilding positionOfBuilding);

	void levelUp(String playerName, int position);

	List<Integer> getInfo(String playerName, int position);
}

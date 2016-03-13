package org.exschool.pocketworld.city.resources.service;

import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.dto.BuildingInfo;
import org.exschool.pocketworld.dto.PositionOfBuilding;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.resource.model.ResourceType;

public interface CityResourcesService {
    CityResourcesDto cityResourcesInfo(String playerName);

    boolean createResourceBuilding(PositionOfBuilding positionOfBuilding, String playerName);
    
    int getTimeInfo(String type, int level);
    
    boolean checkResources(String playerName, String resourceType, int level);
    
    void reduceResources(Player player, ResourceType resourceType, int level);

	void levelUp(String playerName, int position);

	BuildingInfo getInfo(String playerName, int position);
}

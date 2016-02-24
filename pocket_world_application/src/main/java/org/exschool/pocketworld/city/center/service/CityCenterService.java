package org.exschool.pocketworld.city.center.service;



import java.util.Collection;
import java.util.Set;

import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.city.center.dto.CityCenterDto;
import org.exschool.pocketworld.dto.BuildingInfo;
import org.exschool.pocketworld.player.model.Player;

public interface CityCenterService {
    CityCenterDto cityCenterInfo(String playerName);
    
    boolean addBuilding(String playerName, String type, int position);
    
    Collection<String> availableForBuildBuildingTypes(Set<String> builtBuildingTypes);
    
    int getTimeInfo(String type, int level);
    
    boolean checkResources(String playerName, String buildingType, int level);
    
    void reduceResources(Player player, BuildingType buildingType, int level);
	
    BuildingInfo getInfo(String playerName, int position);
	
    void levelUp(String playerName, int position);
}

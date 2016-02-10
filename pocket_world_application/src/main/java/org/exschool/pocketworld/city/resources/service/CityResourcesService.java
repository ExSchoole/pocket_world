package org.exschool.pocketworld.city.resources.service;

import java.util.List;

import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;

public interface CityResourcesService {
    CityResourcesDto cityResourcesInfo(String login);

    boolean createResourceBuilding(String playerName, String type, int position);
    
    void levelUp(String playerName, int position);
	
    List<Integer> getInfo(String playerName, int position);
}

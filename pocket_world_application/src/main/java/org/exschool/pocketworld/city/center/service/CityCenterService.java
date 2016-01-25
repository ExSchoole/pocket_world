package org.exschool.pocketworld.city.center.service;


import org.exschool.pocketworld.city.center.dto.CityCenterDto;

import java.util.Collection;
import java.util.Set;

public interface CityCenterService {
    CityCenterDto cityCenterInfo(String playerName);

    boolean addBuilding(String playerName, String type, int position);
    boolean checkQueue();


    Collection<String> availableForBuildBuildingTypes(Set<String> builtBuildingTypes);
}

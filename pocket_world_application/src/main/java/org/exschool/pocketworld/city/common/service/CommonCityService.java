package org.exschool.pocketworld.city.common.service;

import java.util.List;

import org.exschool.pocketworld.dto.TimeOfBuilding;

/**
 * Created by skandy on 28.01.16.
 */
public interface CommonCityService {
    void buildQueuedBuildings(String playerName);
    List<TimeOfBuilding> getQueuedBuildings(String playerName);
    void changeStatus(String playerName, int position, String type);
}

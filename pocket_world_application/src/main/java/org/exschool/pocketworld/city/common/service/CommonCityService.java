package org.exschool.pocketworld.city.common.service;

import java.util.List;
import java.util.Map;

import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.dto.TimeOfBuilding;

/**
 * Created by skandy on 28.01.16.
 */
public interface CommonCityService {
    void buildQueuedBuildings(String playerName);
    Map<String, List<TimeOfBuilding>> getQueuedBuildings(String playerName);
    boolean changeStatus(String playerName, int position, String type);
}

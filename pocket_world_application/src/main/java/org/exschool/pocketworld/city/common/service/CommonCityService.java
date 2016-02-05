package org.exschool.pocketworld.city.common.service;

import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;

import java.util.List;

/**
 * Created by skandy on 28.01.16.
 */
public interface CommonCityService {
    void buildQueuedBuildings(String playerName);
    List<BuildQueueRecord> getQueuedBuildings(Long playerId);

}

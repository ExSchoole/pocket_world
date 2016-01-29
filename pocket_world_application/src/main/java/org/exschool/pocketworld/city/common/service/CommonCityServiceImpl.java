package org.exschool.pocketworld.city.common.service;

import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.buildQueue.model.Status;
import org.exschool.pocketworld.buildQueue.service.BuildQueueService;
import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.building.service.BuildingService;
import org.exschool.pocketworld.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by skandy on 28.01.16.
 */

@Service
public class CommonCityServiceImpl implements CommonCityService {

    @Autowired
    private BuildQueueService buildQueueService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private BuildingService buildingService;


    @Override
    public void buildQueuedBuildings(String playerName) {
        Long userId = playerService.getPlayerByLogin(playerName).getId();
        List<BuildQueueRecord> records = getQueuedBuildings(userId);
        for (BuildQueueRecord record:records) {
            if (record.getBuildEnd().isBeforeNow()) {
                Building building = buildingService.get(record.getBuildingId());
                building.setLevel(building.getLevel()+1);
                buildingService.save(building);
                buildQueueService.changeStatus(record.getId(),Status.DONE);
            }
        }

    }

    @Override
    public List<BuildQueueRecord> getQueuedBuildings(Long userId) {
        List<BuildQueueRecord> allRecords = buildQueueService.getAllByUser(userId);
        List<BuildQueueRecord> queuedRecords = new LinkedList<>();
        for (BuildQueueRecord record: allRecords) {
            if (record.getStatus().equals(Status.QUEUED)) {
                queuedRecords.add(record);
            }
        }
        return queuedRecords;
    }
}

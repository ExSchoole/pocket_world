package org.exschool.pocketworld.city.common.service;

import java.util.ArrayList;
import java.util.List;

import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.buildQueue.model.Status;
import org.exschool.pocketworld.buildQueue.model.Type;
import org.exschool.pocketworld.buildQueue.service.BuildQueueService;
import org.exschool.pocketworld.dto.TimeOfBuilding;
import org.exschool.pocketworld.player.service.PlayerService;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by skandy on 28.01.16.
 */

@Service
public class CommonCityServiceImpl implements CommonCityService {

    @Autowired
    private BuildQueueService buildQueueService;
    @Autowired
    private PlayerService playerService;

    @Override
    public void buildQueuedBuildings(String playerName) {
        Long userId = playerService.getPlayerByLogin(playerName).getId();
        buildQueueService.updateAll(Status.DONE, userId, Type.BUILDING); 
        buildQueueService.updateAll(Status.DONE, userId, Type.RESOURCE_BUILDING); 
    }
    
    public List<TimeOfBuilding> getQueuedBuildings(String playerName){	
    	List<TimeOfBuilding> currentQueue = new ArrayList<>(); 
    	for (BuildQueueRecord r : buildQueueService.getAllByUserStatus(playerService.getPlayerByLogin(playerName).getId(),Status.QUEUED)){
    		currentQueue.add(new TimeOfBuilding(r.getPosition(),Seconds.secondsBetween(new DateTime(System.currentTimeMillis()), new DateTime(r.getBuildEnd())).getSeconds(), r.getType().name().toLowerCase()));
    	}	
    	return currentQueue;
    }
    
    public void changeStatus(String playerName, int position, String type){
    	buildQueueService.updateStatus(Status.DONE, position, type, playerService.getPlayerByLogin(playerName).getId());
    }
}

package org.exschool.pocketworld.city.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    public Map<String, List<TimeOfBuilding>> getQueuedBuildings(String playerName){	
    	Map<String, List<TimeOfBuilding>> currentQueue = new HashMap<>(); 
    	
    	for (Type t : Type.asList()){
    		System.out.println(t);
    		currentQueue.put(t.name().toLowerCase(), new ArrayList<TimeOfBuilding>());
    		for (BuildQueueRecord r : buildQueueService.getAllByUserStatusType(playerService.getPlayerByLogin(playerName).getId(),Status.QUEUED, t)){
        		currentQueue.get(t.name().toLowerCase()).add(new TimeOfBuilding(r.getPosition(),Seconds.secondsBetween(new DateTime(System.currentTimeMillis()), r.getBuildEnd()).getSeconds()));
        	}
    	}
    
    	return currentQueue;
    }
    
    public boolean changeStatus(String playerName, int position, String type){
    	buildQueueService.updateStatus(Status.DONE, position, type, playerService.getPlayerByLogin(playerName).getId());
    	return true;
    }
}

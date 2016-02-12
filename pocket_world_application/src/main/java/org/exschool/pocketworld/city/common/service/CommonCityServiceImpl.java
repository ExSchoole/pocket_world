package org.exschool.pocketworld.city.common.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.buildQueue.model.Status;
import org.exschool.pocketworld.buildQueue.model.Type;
import org.exschool.pocketworld.buildQueue.service.BuildQueueService;
import org.exschool.pocketworld.building.service.BuildingService;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.dto.TimeOfBuilding;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.resource.building.service.ResourceBuildingService;
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
    @Autowired
    private CityService cityService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private ResourceBuildingService resourceBuildingService;

    @Override
    public void buildQueuedBuildings(String playerName) {
        Long userId = playerService.getPlayerByLogin(playerName).getId();        
        Map<Type, ArrayList<Long>> ids = new HashMap<>();
        for (Type t : Type.values())
        	ids.put(t, new ArrayList<Long>());

        Date date = new Date();
        for (BuildQueueRecord r : buildQueueService.getAllByUserStatusDate(userId, Status.QUEUED, date))
        	ids.get(r.getType()).add(r.getBuildingId());
        
        buildQueueService.updateAll(Status.DONE, userId, date); 
        buildingService.increaseLevel(cityService.getCityId(userId), ids.get(Type.BUILDING));
        resourceBuildingService.increaseLevel(cityService.getCityId(userId), ids.get(Type.RESOURCE_BUILDING));
    }
    
    public List<TimeOfBuilding> getQueuedBuildings(String playerName){	
    	List<TimeOfBuilding> currentQueue = new ArrayList<>(); 

    	for (BuildQueueRecord r : buildQueueService.getAllByUserStatus(
    							  playerService.getPlayerByLogin(playerName).getId(),
    							  Status.QUEUED)){
    		currentQueue.add(new TimeOfBuilding(r.getPosition(),Seconds.secondsBetween(
    						 new DateTime(System.currentTimeMillis()), 
    						 new DateTime(r.getBuildEnd())).getSeconds(), 
    						 r.getType().name().toLowerCase(),
    						 r.getName()));
    	}	
    	return currentQueue;
    }
    
    public void changeStatus(String playerName, int position, String type){
    	buildQueueService.updateStatus(Status.DONE, position, playerService.getPlayerByLogin(playerName).getId(), type);
    	Long userId = playerService.getPlayerByLogin(playerName).getId();
    	if (type.equals(Type.BUILDING.name().toLowerCase()))
    		buildingService.increaseLevel(cityService.getCityId(userId), 
    					new ArrayList<Long>(Arrays.asList(
    						buildingService.getAtPosition(userId, position).getId())));
    	else 
    		resourceBuildingService.increaseLevel(cityService.getCityId(userId), 
    					new ArrayList<Long>(Arrays.asList(
    						resourceBuildingService.getAtPosition(userId, position).getId())));
    }
}

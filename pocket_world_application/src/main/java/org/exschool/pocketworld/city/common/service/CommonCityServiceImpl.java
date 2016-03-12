package org.exschool.pocketworld.city.common.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.buildQueue.model.Status;
import org.exschool.pocketworld.buildQueue.model.Type;
import org.exschool.pocketworld.buildQueue.service.BuildQueueService;
import org.exschool.pocketworld.building.service.BuildingService;
import org.exschool.pocketworld.chat.model.Message;
import org.exschool.pocketworld.chat.service.ChatService;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.dto.TimeOfBuilding;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.building.model.ResourceProduction;
import org.exschool.pocketworld.resource.building.service.ResourceBuildingService;
import org.exschool.pocketworld.resource.building.service.ResourceProductionService;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.exschool.pocketworld.util.builder.MessageBuilder;
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
    @Autowired
    private ResourceProductionService resourceSpeedService;
	@Autowired
	private ChatService chatService;

    @Override
    public void buildQueuedBuildings(String playerName) {
    	Player player = playerService.getPlayerByLogin(playerName);
        Map<Type, ArrayList<Long>> ids = new HashMap<>();
        for (Type t : Type.values())
        	ids.put(t, new ArrayList<Long>());

        Date date = new Date();
        
        Entry<ResourceProduction, Integer> timeBetweenDates = 
    			resourceSpeedService.getIncreaseUpdateDate(player.getId(), date);
        for (ResourceType r : ResourceType.values())
    		player.getPlayerResources().setAmount(r, player.getPlayerResources().getAmount(r)+
    						timeBetweenDates.getKey().getSpeed(r)*timeBetweenDates.getValue());
    	
        int differenceBetweenProduction, timeBetweenNowBuildEnd;
        for (BuildQueueRecord r : buildQueueService.getAllByUserStatusDate(player.getId(), Status.QUEUED, date)){
        	ids.get(r.getType()).add(r.getBuildingId());
        	if (r.getType() == Type.RESOURCE_BUILDING){
        		differenceBetweenProduction = 
        				resourceBuildingService.getDifferenceBetweenProductionByBuildingTypeLevel(
        				ResourceType.valueOf(r.getName().toUpperCase()), 
        									 r.getLevel());
        		
        		resourceSpeedService.updateSpeed(player.getId(), ResourceType.valueOf(
        										 r.getName().toUpperCase()), 
        										 differenceBetweenProduction);
        		
        		timeBetweenNowBuildEnd = 
        				Seconds.secondsBetween(new DateTime(r.getBuildEnd()), 
        									   new DateTime(date)).getSeconds();
        		
        		player.getPlayerResources().setAmount(
        				ResourceType.valueOf(r.getName().toUpperCase()), 
        				player.getPlayerResources().getAmount(
        						ResourceType.valueOf(r.getName().toUpperCase()))+
        				differenceBetweenProduction*timeBetweenNowBuildEnd);
        	}
        }
      
        playerService.savePlayer(player);  
        buildQueueService.updateAll(Status.DONE, player.getId(), date); 
        buildingService.increaseLevel(cityService.getCityId(player.getId()), ids.get(Type.BUILDING));
        resourceBuildingService.increaseLevel(cityService.getCityId(player.getId()), ids.get(Type.RESOURCE_BUILDING));
    }
    
    public List<TimeOfBuilding> getQueuedBuildings(String playerName){	
    	List<TimeOfBuilding> currentQueue = new ArrayList<>(); 

    	for (BuildQueueRecord r : buildQueueService.getAllByUserStatus(
    							  playerService.getPlayerByLogin(playerName).getId(),
    							  Status.QUEUED)){
    		currentQueue.add(new TimeOfBuilding(r.getPosition(),Seconds.secondsBetween(
    						 new DateTime(System.currentTimeMillis()), 
    						 new DateTime(r.getBuildEnd())).getSeconds(), 
    						 r.getLevel(),
    						 r.getType().name().toLowerCase(),
    						 r.getName()));
    	}	
    	return currentQueue;
    }
    
    public void changeStatus(String playerName, int position, String type){
    	buildQueueService.updateStatus(Status.DONE, position, playerService.getPlayerByLogin(playerName).getId(), type);
    	Long userId = playerService.getPlayerByLogin(playerName).getId();
    	Long cityId = cityService.getCityId(userId);
    	if (type.equals(Type.BUILDING.name().toLowerCase())){
    		ArrayList<Long> list = new ArrayList<Long>(Arrays.asList(
					buildingService.getAtPosition(cityId, position).getId()));
    		buildingService.increaseLevel(cityId, list);
    	}else{ 
    		resourceBuildingService.increaseLevel(cityId, 
    					new ArrayList<Long>(Arrays.asList(
    						resourceBuildingService.getAtPosition(cityId, position).getId())));
    		
    		
    		ResourceBuilding resourceBuilding = resourceBuildingService.getAtPosition(cityId, position);
    		resourceSpeedService.updateSpeed(userId, resourceBuilding.getResourceType(), 
    				resourceBuildingService.getDifferenceBetweenProductionByBuildingTypeLevel(
    						resourceBuilding.getResourceType(), resourceBuilding.getLevel()));
    	}
    }

	public Message sendMessage(String sender, String recipient, String message){
            if (playerService.getPlayerByLogin(recipient.toLowerCase())==null) {
                return null;
            }

            Message messageEntity = new MessageBuilder()
					.message(message)
					.recipient(recipient)
					.sender(sender)
					.time(new DateTime().toDate()).build();

			return chatService.save(messageEntity);
	}

	public List<Message> getAllMessages(String playerName){
		return chatService.getAllByPlayerName(playerName);
	}
}

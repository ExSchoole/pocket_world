package org.exschool.pocketworld.city.common.service;

import java.util.*;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.buildQueue.model.Status;
import org.exschool.pocketworld.buildQueue.model.Type;
import org.exschool.pocketworld.buildQueue.service.BuildQueueService;
import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.building.service.BuildingService;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.building.service.ResourceBuildingService;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by skandy on 28.01.16.
 */

@Component
public class BuildServiceImpl implements BuildService {

    @Autowired
    private BuildQueueService buildQueueService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private ResourceBuildingService resourceBuildingService;


    @Override
    public void buildQueuedBuildings(String playerName) {
        Long userId = playerService.getPlayerByLogin(playerName).getId();
        List<BuildQueueRecord> records = getQueuedBuildings(userId);
        for (BuildQueueRecord record:records) {
            if (record.getBuildEnd().isBeforeNow()) {
            	if (record.getType().equals(Type.BUILDING)){
            		Building building = buildingService.get(record.getBuildingId());
            		building.setLevel(building.getLevel()+1);
            		buildingService.save(building);
            	}
            	else{
            		ResourceBuilding resourceBuilding = resourceBuildingService.get(record.getBuildingId());
            		resourceBuilding.setLevel(resourceBuilding.getLevel()+1);
            		resourceBuildingService.save(resourceBuilding);
            	}
            	
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
    
    public Map<String, Map<Integer,Integer>> getQueuedBuildings(String playerName){    	
    	Map<String, Map<Integer,Integer>> currentQueue = new HashMap<>(); 
    	currentQueue.put(Type.BUILDING.name().toLowerCase(), new HashMap<Integer, Integer>());
    	currentQueue.put(Type.RESOURCE_BUILDING.name().toLowerCase(), new HashMap<Integer, Integer>());
    	
    	List<BuildQueueRecord> buildingQueue = buildQueueService.getAllByUser(playerService.getPlayerByLogin(playerName).getId());
    	for (BuildQueueRecord b : buildingQueue){
    		if (b.getStatus().equals(Status.QUEUED)){
    			if (b.getType().equals(Type.BUILDING))
    				currentQueue.get(b.getType().name().toLowerCase()).put(buildingService.get(b.getBuildingId()).getPosition(), Seconds.secondsBetween(new DateTime(System.currentTimeMillis()), b.getBuildEnd()).getSeconds());
    			else
    				currentQueue.get(b.getType().name().toLowerCase()).put(resourceBuildingService.get(b.getBuildingId()).getPosition(), Seconds.secondsBetween(new DateTime(System.currentTimeMillis()), b.getBuildEnd()).getSeconds());
    		}
    	}

    	return currentQueue;
    }
    
    public boolean changeStatus(String playerName, int position, String type){
    	List<BuildQueueRecord> buildingQueue = buildQueueService.getAllByUser(playerService.getPlayerByLogin(playerName).getId());
    	if (type.equals(Type.BUILDING.name().toLowerCase()))
    	for (BuildQueueRecord b : buildingQueue){
    		if (buildingService.get(b.getBuildingId()).getPosition() == position && b.getBuildEnd().isBeforeNow()){
    			buildQueueService.changeStatus(b.getId(),Status.DONE);
    			return true;
    		}
    	}
    	else
    		for (BuildQueueRecord b : buildingQueue){
        		if (resourceBuildingService.get(b.getBuildingId()).getPosition() == position && b.getBuildEnd().isBeforeNow()){
        			buildQueueService.changeStatus(b.getId(),Status.DONE);
        			return true;
        		}
        	}
    	
    	return false;
    }

	@Override
	public Collection activeTimers(Long playerId) {
		List<BuildQueueRecord> activeRecords = buildQueueService.getAllActiveByUser(playerId);
		Collection<BuildQueueRecord> notBuilt = Collections2.filter(activeRecords, new Predicate<BuildQueueRecord>() {
			@Override
			public boolean apply(BuildQueueRecord input) {
				return input.getBuildEnd().isAfterNow();
			}
		});
		//transform to timers
		return Arrays.asList(Timer.create().type(Type.BUILDING.name().toLowerCase()).buildEndInSeconds(1000L).position(3).get(),
				Timer.create().type(Type.BUILDING.name().toLowerCase()).buildEndInSeconds(1000L).position(3).get(),
				Timer.create().type(Type.RESOURCE_BUILDING.name().toLowerCase()).buildEndInSeconds(1000L).position(3).get());
	}

	@Override
	public void buildCompleted(Long playerId) {

	}

	@Override
	public void build(Long playerId) {

	}

	private static class Timer {

		private final String type;
		private final long buildEndInSeconds;
		private final int position;

		private Timer(String type, long buildEndInSeconds, int position) {
			this.type = type;
			this.buildEndInSeconds = buildEndInSeconds;
			this.position = position;
		}

		public String getType() {
			return type;
		}

		public long getBuildEndInSeconds() {
			return buildEndInSeconds;
		}

		public int getPosition() {
			return position;
		}

		public static Builder create() {
			return new Builder();
		}

		private static class Builder {

			private String type;
			private long buildEndInSeconds;
			private int position;

			public Builder type(String type) {
				this.type = type;
				return this;
			}

			public Builder buildEndInSeconds(long seconds) {
				this.buildEndInSeconds = seconds;
				return this;
			}

			public Builder position(int position) {
				this.position = position;
				return this;
			}


			public Timer get() {
				return new Timer(type, buildEndInSeconds, position);
			}
		}

	}
}

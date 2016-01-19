package org.exschool.pocketworld.building.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.building.model.BuildingResourceId;
import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.building.model.TimeId;
import org.exschool.pocketworld.resource.model.ResourceType;

/**
 * Created by skandy on 12.11.15.
 */
public interface BuildingService {
    List<Building> allBuildings();
    Building get (long id);
    void saveAllInformation();
    Building save(Building entity);
    List<Building> getBuildingsByCityId(Long cityId);
    int getTimeByBuildingTypeLevel(BuildingType buildingType, int level);
    int getResourceByBuildingTypeResourceTypeLevel(BuildingType buildingType, ResourceType resourceType, int level);
    
    Map<BuildingResourceId, Integer> RESOURCE_BUILDINGS_INFO = new HashMap<BuildingResourceId, Integer>(){
    	{
	    	put(new BuildingResourceId(BuildingType.BARN,ResourceType.CLAY,1),100);
	    	put(new BuildingResourceId(BuildingType.FARM,ResourceType.CORN,1),110);
	    	put(new BuildingResourceId(BuildingType.GILOTHOME,ResourceType.GOLD,1),120);
	    	put(new BuildingResourceId(BuildingType.MALL,ResourceType.TIMBER,1),130);
    	};
    };
    
    Map<TimeId, Integer> TIME_BUILDINGS_INFO = new HashMap<TimeId, Integer>(){
    	{
	    	put(new TimeId(BuildingType.BARN,1),5);
	    	put(new TimeId(BuildingType.FARM,1),6);
	    	put(new TimeId(BuildingType.GILOTHOME,1),7);
	    	put(new TimeId(BuildingType.MALL,1),8);
    	};
    };
   
}


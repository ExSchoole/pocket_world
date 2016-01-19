package org.exschool.pocketworld.resource.building.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.exschool.pocketworld.resource.building.model.BuildingResourceId;
import org.exschool.pocketworld.resource.building.model.ProductionId;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.building.model.TimeId;
import org.exschool.pocketworld.resource.model.ResourceType;

/**
 * Created by manoylo on 20.11.15.
 */
public interface ResourceBuildingService {
    List<ResourceBuilding> allBuildings();
    ResourceBuilding get (long id);
    ResourceBuilding save(ResourceBuilding entity);
    List<ResourceBuilding> allCityBuildings(Long id);
    int getTimeByBuildingTypeLevel(ResourceType buildingType, int level);
    int getProductionByBuildingTypeLevel(ResourceType buildingType, int level);
    int getResourcesByBuildingTypeLevel(ResourceType buildingType,ResourceType resourceType, int level);
    void saveAllInformation();
    
    Map<BuildingResourceId, Integer> RESOURCE_BUILDINGS_INFO = new HashMap<BuildingResourceId, Integer>(){
    	{	  
    		put(new BuildingResourceId(ResourceType.CLAY,ResourceType.CLAY,1),100);
    		put(new BuildingResourceId(ResourceType.CLAY,ResourceType.CORN,1),110);
    		put(new BuildingResourceId(ResourceType.CLAY,ResourceType.GOLD,1),120);
    		put(new BuildingResourceId(ResourceType.CLAY,ResourceType.TIMBER,1),130);
    	};	  
    };
  
    Map<TimeId, Integer> TIME_RESOURCE_BUILDINGS_INFO = new HashMap<TimeId, Integer>(){
    	{	  
    		put(new TimeId(ResourceType.CLAY,1),5);
    		put(new TimeId(ResourceType.CORN,1),6);
    		put(new TimeId(ResourceType.TIMBER,1),7);
    		put(new TimeId(ResourceType.GOLD,1),8);	  
    	};
    };
  
    Map<ProductionId, Integer> PRODUCTION_RESOURCE_BUILDINGS_INFO = new HashMap<ProductionId, Integer>(){
    	{	  
    		put(new ProductionId(ResourceType.CLAY,1),100);
    		put(new ProductionId(ResourceType.CORN,1),110);
    		put(new ProductionId(ResourceType.TIMBER,1),120);
    		put(new ProductionId(ResourceType.GOLD,1),130);
    	};	  
    };
}


package org.exschool.pocketworld.building.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.building.model.BuildingResource;
import org.exschool.pocketworld.building.model.BuildingResourceId;
import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.building.model.Time;
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
    Map<ResourceType,Integer> getResourcesByBuildingTypeLevel(BuildingType buildingType, int level);
    
    List<BuildingResource> RESOURCE_BUILDINGS_INFO = Arrays.asList(
			  
			  new BuildingResource(new BuildingResourceId(BuildingType.BARN,ResourceType.CLAY,1),100),
			  new BuildingResource(new BuildingResourceId(BuildingType.FARM,ResourceType.CORN,1),110),
			  new BuildingResource(new BuildingResourceId(BuildingType.GILOTHOME,ResourceType.GOLD,1),120),
			  new BuildingResource(new BuildingResourceId(BuildingType.MALL,ResourceType.TIMBER,1),130)
			  
			  );
    
    List<Time> TIME_BUILDINGS_INFO = Arrays.asList(
			  
			  new Time(new TimeId(BuildingType.BARN,1),5),
			  new Time(new TimeId(BuildingType.FARM,1),6),
			  new Time(new TimeId(BuildingType.GILOTHOME,1),7),
			  new Time(new TimeId(BuildingType.MALL,1),8)
			  
			  );
}


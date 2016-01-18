package org.exschool.pocketworld.resource.building.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.exschool.pocketworld.resource.building.model.BuildingResource;
import org.exschool.pocketworld.resource.building.model.BuildingResourceId;
import org.exschool.pocketworld.resource.building.model.Production;
import org.exschool.pocketworld.resource.building.model.ProductionId;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.building.model.ResourceBuildingTime;
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
    Map<ResourceType,Integer> getResourcesByBuildingTypeLevel(ResourceType buildingType, int level);
    void saveAllInformation();
    
    List<BuildingResource> RESOURCE_BUILDINGS_INFO = Arrays.asList(
			  
			  new BuildingResource(new BuildingResourceId(ResourceType.CLAY,ResourceType.CLAY,1),100),
			  new BuildingResource(new BuildingResourceId(ResourceType.CLAY,ResourceType.CORN,1),110),
			  new BuildingResource(new BuildingResourceId(ResourceType.CLAY,ResourceType.GOLD,1),120),
			  new BuildingResource(new BuildingResourceId(ResourceType.CLAY,ResourceType.TIMBER,1),130)
			  
			  );
  
    List<ResourceBuildingTime> TIME_RESOURCE_BUILDINGS_INFO = Arrays.asList(
			  
			  new ResourceBuildingTime(new TimeId(ResourceType.CLAY,1),5),
			  new ResourceBuildingTime(new TimeId(ResourceType.CORN,1),6),
			  new ResourceBuildingTime(new TimeId(ResourceType.TIMBER,1),7),
			  new ResourceBuildingTime(new TimeId(ResourceType.GOLD,1),8)
			  
			  );
  
    List<Production> PRODUCTION_RESOURCE_BUILDINGS_INFO = Arrays.asList(
		  
		  new Production(new ProductionId(ResourceType.CLAY,1),100),
		  new Production(new ProductionId(ResourceType.CORN,1),110),
		  new Production(new ProductionId(ResourceType.TIMBER,1),120),
		  new Production(new ProductionId(ResourceType.GOLD,1),130)
		  
		  );
}


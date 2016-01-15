package org.exschool.pocketworld.info.resourcebuilding;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.exschool.pocketworld.resource.model.ResourceType;

public interface ResourceBuildingInfoService {
	List<ResourceBuildingInfo> allBuildings();
	Collection<ResourceBuildingInfo> saveAll();
	 
	List<ResourceBuildingInfo> RESOURCE_BUILDINGS_INFO = Arrays.asList(
			
			 new ResourceBuildingInfo(ResourceType.CLAY,1,5,2,1,2,3,4),
			 new ResourceBuildingInfo(ResourceType.CLAY,2,10,4,5,6,7,8),
			 new ResourceBuildingInfo(ResourceType.CLAY,3,15,8,9,10,11,12),
			 
			 
			 new ResourceBuildingInfo(ResourceType.CORN,1,5,2,1,3,2,4),
			 new ResourceBuildingInfo(ResourceType.CORN,2,10,4,5,7,6,8),
			 new ResourceBuildingInfo(ResourceType.CORN,3,15,8,9,11,10,12),
			 
			 new ResourceBuildingInfo(ResourceType.GOLD,1,5,2,4,2,3,1),
			 new ResourceBuildingInfo(ResourceType.GOLD,2,10,4,8,6,7,5),
			 new ResourceBuildingInfo(ResourceType.GOLD,3,15,8,12,10,11,9),
			 
			 new ResourceBuildingInfo(ResourceType.TIMBER,1,5,2,4,3,2,1),
			 new ResourceBuildingInfo(ResourceType.TIMBER,2,10,4,8,7,6,5),
			 new ResourceBuildingInfo(ResourceType.TIMBER,3,15,8,12,11,10,9)
			
			);
}

package org.exschool.pocketworld.resource.building.service;

import java.util.Date;
import java.util.Map.Entry;

import org.exschool.pocketworld.resource.building.model.ResourceProduction;
import org.exschool.pocketworld.resource.model.ResourceType;

public interface ResourceProductionService {
	
	ResourceProduction addResourceSpeed(ResourceProduction entity);
	
	ResourceProduction updateSpeed(Long playerId, ResourceType resourceType, int delta);
	
	Entry<ResourceProduction, Integer> getIncreaseUpdateDate(Long playerId, Date date);
	
	ResourceProduction createResourceSpeed(Long playerId, Date date);
}

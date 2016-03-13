package org.exschool.pocketworld.resource.building.service;

import java.util.AbstractMap;
import java.util.Date;
import java.util.Map.Entry;

import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.resource.building.model.ResourceProduction;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("resourceProductionService")
@Transactional
public class ResourceProductionServiceImpl implements ResourceProductionService {
	
	@Autowired
    private Dao dao;
	
	@Override
	public ResourceProduction updateSpeed(Long playerId, ResourceType resourceType, int delta) {
		ResourceProduction resourceSpeed = dao.get(ResourceProduction.class, playerId);
		switch (resourceType){
			case GOLD :   resourceSpeed.setGoldDelta(delta); break;
			case TIMBER : resourceSpeed.setTimberDelta(delta); break;
			case CLAY :   resourceSpeed.setClayDelta(delta); break;
			case CORN :   resourceSpeed.setCornDelta(delta); break;
		}
		
		return dao.save(resourceSpeed);
	}

	@Override
	public Entry<ResourceProduction, Integer> getIncreaseUpdateDate(Long playerId, Date date) {
		ResourceProduction resourceSpeed = dao.get(ResourceProduction.class, playerId);
		int timeBetweenDates = 
				Seconds.secondsBetween(new DateTime(resourceSpeed.getLastUpdate()),
									   new DateTime(date))
					   .getSeconds();		
		
		resourceSpeed.setLastUpdate(date);
		dao.save(resourceSpeed);
		
		return new AbstractMap.SimpleEntry<>(resourceSpeed, timeBetweenDates);
	}

	@Override
	public ResourceProduction createResourceSpeed(Long playerId, Date date){
		return dao.save(new ResourceProduction(playerId,0,0,0,0,date));
	}
	
	@Override
	public ResourceProduction addResourceSpeed(ResourceProduction entity) {
		return dao.save(entity);
	}

	public void setDao(Dao dao) {
        this.dao = dao;
    }
	
}

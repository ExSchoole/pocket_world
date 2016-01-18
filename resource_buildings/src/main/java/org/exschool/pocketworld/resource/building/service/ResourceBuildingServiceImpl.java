package org.exschool.pocketworld.resource.building.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.resource.building.model.BuildingResource;
import org.exschool.pocketworld.resource.building.model.Production;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.building.model.ResourceBuildingTime;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by manoylo on 12.11.15.
 */
@Service("resourceBuildingService")
@Transactional
public class ResourceBuildingServiceImpl  implements ResourceBuildingService {
	/**
     * dao - data access object
     */
    @Autowired
    private Dao dao;

    /**
     * Gets all ResourceBuilding from DB
     *
     * @return list of ResourceBuilding
     */
    @Override
    public List<ResourceBuilding> allBuildings() {
        return dao.all(ResourceBuilding.class);
    }
    @Override
    public List<ResourceBuilding> allCityBuildings(Long id) {
    	DetachedCriteria query = DetachedCriteria.forClass(ResourceBuilding.class);
        query.add(Restrictions.eq("cityId", id));
        return dao.getAllBy(query);
    }
    /**
     * Returns ResourceBuilding by id
     *
     * @param id
     * @return ResourceBuilding object
     */
    @Override
    public ResourceBuilding get(long id) {
        return dao.get(ResourceBuilding.class, id);
    }

    /**
     * Saves entity object to DB
     *
     * @param entity
     */
    @Override
    public ResourceBuilding save(ResourceBuilding entity) {
        return dao.save(entity);
    }

    /**
     * Setter for dao
     *
     * @param dao
     */
    public void setDao(Dao dao) {
        this.dao = dao;
    }
	@Override
	public void saveAllInformation() {
		dao.saveAll(RESOURCE_BUILDINGS_INFO);
		dao.saveAll(TIME_RESOURCE_BUILDINGS_INFO);
		dao.saveAll(PRODUCTION_RESOURCE_BUILDINGS_INFO);
	}
	
	@Override
	public int getProductionByBuildingTypeLevel(ResourceType buildingType, int level) {
		for (Production p : PRODUCTION_RESOURCE_BUILDINGS_INFO )
			if (p.getProductionId().getResourceType().equals(buildingType)==true && 
				p.getProductionId().getLevel()==level)
				return p.getAmount();
		
		return -1;
	}
	
	@Override
	public int getTimeByBuildingTypeLevel(ResourceType buildingType, int level) {
		for (ResourceBuildingTime t : TIME_RESOURCE_BUILDINGS_INFO)
			if (t.getTimeId().getBuildingType().equals(buildingType)==true && 
				t.getTimeId().getLevel()==level)
				return t.getTime();
		
		return -1;
	}

	@Override
	public Map<ResourceType, Integer> getResourcesByBuildingTypeLevel(ResourceType buildingType, int level) {
		Map<ResourceType, Integer> resources = new HashMap<>();
		for (BuildingResource b : RESOURCE_BUILDINGS_INFO)
			if (b.getBuildingResourceId().getBuildingType().equals(buildingType)==true && 
				b.getBuildingResourceId().getLevel()==level)
				resources.put(b.getBuildingResourceId().getResourceType(), b.getAmount());
		
		return resources;
	}
    
   
}

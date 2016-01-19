package org.exschool.pocketworld.resource.building.service;

import java.util.List;
import java.util.Map.Entry;

import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.resource.building.model.BuildingResource;
import org.exschool.pocketworld.resource.building.model.BuildingResourceId;
import org.exschool.pocketworld.resource.building.model.Production;
import org.exschool.pocketworld.resource.building.model.ProductionId;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.building.model.ResourceBuildingTime;
import org.exschool.pocketworld.resource.building.model.TimeId;
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
		for (Entry<BuildingResourceId, Integer> b : RESOURCE_BUILDINGS_INFO.entrySet())
    		dao.save(new BuildingResource(b.getKey(),b.getValue()));
    	
    	for (Entry<TimeId, Integer> t : TIME_RESOURCE_BUILDINGS_INFO.entrySet())
    		dao.save(new ResourceBuildingTime(t.getKey(),t.getValue()));
    	
    	for (Entry<ProductionId, Integer> p : PRODUCTION_RESOURCE_BUILDINGS_INFO.entrySet())
    		dao.save(new Production(p.getKey(),p.getValue()));
	}
	
	@Override
	public int getProductionByBuildingTypeLevel(ResourceType buildingType, int level) {
		return PRODUCTION_RESOURCE_BUILDINGS_INFO.get(new ProductionId(buildingType,level));
	}
	
	@Override
	public int getTimeByBuildingTypeLevel(ResourceType buildingType, int level) {
		return TIME_RESOURCE_BUILDINGS_INFO.get(new TimeId(buildingType,level));
	}

	@Override
	public int getResourcesByBuildingTypeLevel(ResourceType buildingType, ResourceType resourceType, int level) {
		return RESOURCE_BUILDINGS_INFO.get(new BuildingResourceId(buildingType,resourceType,level));
	}
    
   
}

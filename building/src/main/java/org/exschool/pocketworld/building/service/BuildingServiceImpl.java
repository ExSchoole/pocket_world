package org.exschool.pocketworld.building.service;


import java.util.List;
import java.util.Map.Entry;

import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.building.model.BuildingResource;
import org.exschool.pocketworld.building.model.BuildingResourceId;
import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.building.model.Time;
import org.exschool.pocketworld.building.model.TimeId;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by skandy on 12.11.15.
 */


@Service("buildingService")
@Transactional
public class BuildingServiceImpl implements BuildingService {
    /**
     * dao - data access object
     */
    @Autowired
    private Dao dao;

    /**
     * Gets all buildings from DB
     *
     * @return list of buildings
     */
    @Override
    public List<Building> allBuildings() {
        return dao.all(Building.class);
    }

    /**
     * Returns building by id
     *
     * @param id
     * @return Building object
     */
    @Override
    public Building get(long id) {
        return dao.get(Building.class, id);
    }


    @Override
    public List<Building> getBuildingsByCityId(Long cityId) {
        DetachedCriteria query = DetachedCriteria.forClass(Building.class);
        query.add(Restrictions.eq("cityId", cityId));
        return dao.getAllBy(query);
    }
    
    /**
     * Saves entity object to DB
     *
     * @param entity
     */
    @Override
    public Building save(Building entity) {
        return dao.save(entity);
    }
    
    @Override
	public void saveAllInformation() {
    	for (Entry<BuildingResourceId, Integer> b : RESOURCE_BUILDINGS_INFO.entrySet())
    		dao.save(new BuildingResource(b.getKey(),b.getValue()));
    	
    	for (Entry<TimeId, Integer> t : TIME_BUILDINGS_INFO.entrySet())
    		dao.save(new Time(t.getKey(),t.getValue()));
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
	public int getTimeByBuildingTypeLevel(BuildingType buildingType, int level) {
		return TIME_BUILDINGS_INFO.get(new TimeId(buildingType,level));
	}

	@Override
	public int getResourceByBuildingTypeResourceTypeLevel(BuildingType buildingType,
			ResourceType resourceType, int level) {
		return RESOURCE_BUILDINGS_INFO.get(new BuildingResourceId(buildingType, resourceType, level));
	}
}

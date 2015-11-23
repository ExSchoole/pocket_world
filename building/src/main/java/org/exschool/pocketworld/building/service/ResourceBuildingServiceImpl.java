package org.exschool.pocketworld.building.service;

import org.exschool.pocketworld.building.model.ResourceBuilding;
import org.exschool.pocketworld.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


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
    
   
}

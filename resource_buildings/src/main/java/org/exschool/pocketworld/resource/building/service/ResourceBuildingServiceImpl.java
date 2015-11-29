package org.exschool.pocketworld.resource.building.service;

import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
<<<<<<< HEAD
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
=======
>>>>>>> origin/master
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
<<<<<<< HEAD
    @Override
    public List<ResourceBuilding> allCityBuildings(Long id) {
    	DetachedCriteria query = DetachedCriteria.forClass(ResourceBuilding.class);
        query.add(Restrictions.eq("cityId", id));
        return dao.getAllBy(query);
    }
=======

>>>>>>> origin/master
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

package org.exschool.pocketworld.building.service;


import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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

    /**
     * Saves entity object to DB
     *
     * @param entity
     */
    @Override
    public Building save(Building entity) {
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

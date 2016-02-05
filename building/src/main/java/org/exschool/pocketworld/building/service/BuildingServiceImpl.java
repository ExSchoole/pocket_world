package org.exschool.pocketworld.building.service;


import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.util.builder.BuildingBuilder;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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

    @Override
    public Long createBuilding(Long cityId, BuildingType buildingType, int position, int level) {
        Building building = BuildingBuilder.builder()
                .buildingType(buildingType)
                .cityId(cityId)
                .level(level)
                .position(position)
                .build();
        save(building);
        return building.getId();
    }

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
     * Returns building for specific position and city or null if
     * the position is empty
     *
     * @param cityId   cityId
     * @param position position of building
     */
    public Building getAtPosition(Long cityId, Integer position) {
        DetachedCriteria dc = DetachedCriteria.forClass(Building.class);
        dc.add(Restrictions.eq("cityId", cityId));
        dc.add(Restrictions.eq("position", position));
        return (Building) dao.getBy(dc);
    }

    @Override
    public Boolean isBuildingExist(Long cityId, Integer position) {
        return getAtPosition(cityId, position) != null;
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

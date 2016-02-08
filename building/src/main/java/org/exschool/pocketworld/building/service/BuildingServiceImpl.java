package org.exschool.pocketworld.building.service;


import org.exschool.pocketworld.building.model.*;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.exschool.pocketworld.util.builder.BuildingBuilder;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


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

    private final Map<BuildingResourceId, Integer> RESOURCE_BUILDINGS_INFO;
    private final Map<TimeId, Integer> TIME_BUILDINGS_INFO;

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
            dao.save(new BuildingResource(b.getKey(), b.getValue()));

        for (Entry<TimeId, Integer> t : TIME_BUILDINGS_INFO.entrySet())
            dao.save(new Time(t.getKey(), t.getValue()));
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

    @Override
    public int getTimeByBuildingTypeLevel(BuildingType buildingType, int level) {
        return TIME_BUILDINGS_INFO.get(new TimeId(buildingType, level));
    }

    @Override
    public int getResourceByBuildingTypeResourceTypeLevel(BuildingType buildingType,
                                                          ResourceType resourceType, int level) {
        return RESOURCE_BUILDINGS_INFO.get(new BuildingResourceId(buildingType, resourceType, level));
    }

    @Override
    public Map<BuildingResourceId, Integer> getResourceBuildingInfo() {
        return RESOURCE_BUILDINGS_INFO;
    }

    @Override
    public Map<TimeId, Integer> getTimeInfo() {
        return TIME_BUILDINGS_INFO;
    }

    {
        RESOURCE_BUILDINGS_INFO = new HashMap<BuildingResourceId, Integer>() {
            {
                put(new BuildingResourceId(BuildingType.BARN, ResourceType.CLAY, 1), 10);
                put(new BuildingResourceId(BuildingType.BARN, ResourceType.CORN, 1), 15);
                put(new BuildingResourceId(BuildingType.BARN, ResourceType.TIMBER, 1), 20);
                put(new BuildingResourceId(BuildingType.BARN, ResourceType.GOLD, 1), 25);

                put(new BuildingResourceId(BuildingType.BARN, ResourceType.CLAY, 2), 20);
                put(new BuildingResourceId(BuildingType.BARN, ResourceType.CORN, 2), 30);
                put(new BuildingResourceId(BuildingType.BARN, ResourceType.TIMBER, 2), 40);
                put(new BuildingResourceId(BuildingType.BARN, ResourceType.GOLD, 2), 50);

                put(new BuildingResourceId(BuildingType.BARN, ResourceType.CLAY, 3), 40);
                put(new BuildingResourceId(BuildingType.BARN, ResourceType.CORN, 3), 60);
                put(new BuildingResourceId(BuildingType.BARN, ResourceType.TIMBER, 3), 80);
                put(new BuildingResourceId(BuildingType.BARN, ResourceType.GOLD, 3), 100);

                put(new BuildingResourceId(BuildingType.FARM, ResourceType.CLAY, 1), 10);
                put(new BuildingResourceId(BuildingType.FARM, ResourceType.CORN, 1), 25);
                put(new BuildingResourceId(BuildingType.FARM, ResourceType.TIMBER, 1), 15);
                put(new BuildingResourceId(BuildingType.FARM, ResourceType.GOLD, 1), 20);

                put(new BuildingResourceId(BuildingType.FARM, ResourceType.CLAY, 2), 20);
                put(new BuildingResourceId(BuildingType.FARM, ResourceType.CORN, 2), 50);
                put(new BuildingResourceId(BuildingType.FARM, ResourceType.TIMBER, 2), 30);
                put(new BuildingResourceId(BuildingType.FARM, ResourceType.GOLD, 2), 40);

                put(new BuildingResourceId(BuildingType.FARM, ResourceType.CLAY, 3), 40);
                put(new BuildingResourceId(BuildingType.FARM, ResourceType.CORN, 3), 100);
                put(new BuildingResourceId(BuildingType.FARM, ResourceType.TIMBER, 3), 60);
                put(new BuildingResourceId(BuildingType.FARM, ResourceType.GOLD, 3), 80);

                put(new BuildingResourceId(BuildingType.GILOTHOME, ResourceType.CLAY, 1), 10);
                put(new BuildingResourceId(BuildingType.GILOTHOME, ResourceType.CORN, 1), 20);
                put(new BuildingResourceId(BuildingType.GILOTHOME, ResourceType.TIMBER, 1), 25);
                put(new BuildingResourceId(BuildingType.GILOTHOME, ResourceType.GOLD, 1), 15);

                put(new BuildingResourceId(BuildingType.GILOTHOME, ResourceType.CLAY, 2), 20);
                put(new BuildingResourceId(BuildingType.GILOTHOME, ResourceType.CORN, 2), 40);
                put(new BuildingResourceId(BuildingType.GILOTHOME, ResourceType.TIMBER, 2), 50);
                put(new BuildingResourceId(BuildingType.GILOTHOME, ResourceType.GOLD, 2), 30);

                put(new BuildingResourceId(BuildingType.GILOTHOME, ResourceType.CLAY, 3), 40);
                put(new BuildingResourceId(BuildingType.GILOTHOME, ResourceType.CORN, 3), 80);
                put(new BuildingResourceId(BuildingType.GILOTHOME, ResourceType.TIMBER, 3), 100);
                put(new BuildingResourceId(BuildingType.GILOTHOME, ResourceType.GOLD, 3), 60);

                put(new BuildingResourceId(BuildingType.MALL, ResourceType.CLAY, 1), 20);
                put(new BuildingResourceId(BuildingType.MALL, ResourceType.CORN, 1), 10);
                put(new BuildingResourceId(BuildingType.MALL, ResourceType.TIMBER, 1), 15);
                put(new BuildingResourceId(BuildingType.MALL, ResourceType.GOLD, 1), 25);

                put(new BuildingResourceId(BuildingType.MALL, ResourceType.CLAY, 2), 40);
                put(new BuildingResourceId(BuildingType.MALL, ResourceType.CORN, 2), 20);
                put(new BuildingResourceId(BuildingType.MALL, ResourceType.TIMBER, 2), 30);
                put(new BuildingResourceId(BuildingType.MALL, ResourceType.GOLD, 2), 50);

                put(new BuildingResourceId(BuildingType.MALL, ResourceType.CLAY, 3), 80);
                put(new BuildingResourceId(BuildingType.MALL, ResourceType.CORN, 3), 40);
                put(new BuildingResourceId(BuildingType.MALL, ResourceType.TIMBER, 3), 60);
                put(new BuildingResourceId(BuildingType.MALL, ResourceType.GOLD, 3), 100);

                put(new BuildingResourceId(BuildingType.MARKETPLACE, ResourceType.CLAY, 1), 20);
                put(new BuildingResourceId(BuildingType.MARKETPLACE, ResourceType.CORN, 1), 25);
                put(new BuildingResourceId(BuildingType.MARKETPLACE, ResourceType.TIMBER, 1), 10);
                put(new BuildingResourceId(BuildingType.MARKETPLACE, ResourceType.GOLD, 1), 15);

                put(new BuildingResourceId(BuildingType.MARKETPLACE, ResourceType.CLAY, 2), 40);
                put(new BuildingResourceId(BuildingType.MARKETPLACE, ResourceType.CORN, 2), 50);
                put(new BuildingResourceId(BuildingType.MARKETPLACE, ResourceType.TIMBER, 2), 20);
                put(new BuildingResourceId(BuildingType.MARKETPLACE, ResourceType.GOLD, 2), 30);

                put(new BuildingResourceId(BuildingType.MARKETPLACE, ResourceType.CLAY, 3), 80);
                put(new BuildingResourceId(BuildingType.MARKETPLACE, ResourceType.CORN, 3), 100);
                put(new BuildingResourceId(BuildingType.MARKETPLACE, ResourceType.TIMBER, 3), 40);
                put(new BuildingResourceId(BuildingType.MARKETPLACE, ResourceType.GOLD, 3), 60);

                put(new BuildingResourceId(BuildingType.PLANT, ResourceType.CLAY, 1), 20);
                put(new BuildingResourceId(BuildingType.PLANT, ResourceType.CORN, 1), 15);
                put(new BuildingResourceId(BuildingType.PLANT, ResourceType.TIMBER, 1), 25);
                put(new BuildingResourceId(BuildingType.PLANT, ResourceType.GOLD, 1), 10);

                put(new BuildingResourceId(BuildingType.PLANT, ResourceType.CLAY, 2), 40);
                put(new BuildingResourceId(BuildingType.PLANT, ResourceType.CORN, 2), 30);
                put(new BuildingResourceId(BuildingType.PLANT, ResourceType.TIMBER, 2), 50);
                put(new BuildingResourceId(BuildingType.PLANT, ResourceType.GOLD, 2), 20);

                put(new BuildingResourceId(BuildingType.PLANT, ResourceType.CLAY, 3), 80);
                put(new BuildingResourceId(BuildingType.PLANT, ResourceType.CORN, 3), 60);
                put(new BuildingResourceId(BuildingType.PLANT, ResourceType.TIMBER, 3), 100);
                put(new BuildingResourceId(BuildingType.PLANT, ResourceType.GOLD, 3), 40);

                put(new BuildingResourceId(BuildingType.POOL, ResourceType.CLAY, 1), 25);
                put(new BuildingResourceId(BuildingType.POOL, ResourceType.CORN, 1), 10);
                put(new BuildingResourceId(BuildingType.POOL, ResourceType.TIMBER, 1), 15);
                put(new BuildingResourceId(BuildingType.POOL, ResourceType.GOLD, 1), 20);

                put(new BuildingResourceId(BuildingType.POOL, ResourceType.CLAY, 2), 50);
                put(new BuildingResourceId(BuildingType.POOL, ResourceType.CORN, 2), 20);
                put(new BuildingResourceId(BuildingType.POOL, ResourceType.TIMBER, 2), 30);
                put(new BuildingResourceId(BuildingType.POOL, ResourceType.GOLD, 2), 40);

                put(new BuildingResourceId(BuildingType.POOL, ResourceType.CLAY, 3), 100);
                put(new BuildingResourceId(BuildingType.POOL, ResourceType.CORN, 3), 40);
                put(new BuildingResourceId(BuildingType.POOL, ResourceType.TIMBER, 3), 60);
                put(new BuildingResourceId(BuildingType.POOL, ResourceType.GOLD, 3), 80);

                put(new BuildingResourceId(BuildingType.SCHOOL, ResourceType.CLAY, 1), 25);
                put(new BuildingResourceId(BuildingType.SCHOOL, ResourceType.CORN, 1), 20);
                put(new BuildingResourceId(BuildingType.SCHOOL, ResourceType.TIMBER, 1), 10);
                put(new BuildingResourceId(BuildingType.SCHOOL, ResourceType.GOLD, 1), 15);

                put(new BuildingResourceId(BuildingType.SCHOOL, ResourceType.CLAY, 2), 50);
                put(new BuildingResourceId(BuildingType.SCHOOL, ResourceType.CORN, 2), 40);
                put(new BuildingResourceId(BuildingType.SCHOOL, ResourceType.TIMBER, 2), 20);
                put(new BuildingResourceId(BuildingType.SCHOOL, ResourceType.GOLD, 2), 30);

                put(new BuildingResourceId(BuildingType.SCHOOL, ResourceType.CLAY, 3), 100);
                put(new BuildingResourceId(BuildingType.SCHOOL, ResourceType.CORN, 3), 80);
                put(new BuildingResourceId(BuildingType.SCHOOL, ResourceType.TIMBER, 3), 40);
                put(new BuildingResourceId(BuildingType.SCHOOL, ResourceType.GOLD, 3), 60);

                put(new BuildingResourceId(BuildingType.STORAGE, ResourceType.CLAY, 1), 25);
                put(new BuildingResourceId(BuildingType.STORAGE, ResourceType.CORN, 1), 15);
                put(new BuildingResourceId(BuildingType.STORAGE, ResourceType.TIMBER, 1), 20);
                put(new BuildingResourceId(BuildingType.STORAGE, ResourceType.GOLD, 1), 10);

                put(new BuildingResourceId(BuildingType.STORAGE, ResourceType.CLAY, 2), 50);
                put(new BuildingResourceId(BuildingType.STORAGE, ResourceType.CORN, 2), 30);
                put(new BuildingResourceId(BuildingType.STORAGE, ResourceType.TIMBER, 2), 40);
                put(new BuildingResourceId(BuildingType.STORAGE, ResourceType.GOLD, 2), 20);

                put(new BuildingResourceId(BuildingType.STORAGE, ResourceType.CLAY, 3), 100);
                put(new BuildingResourceId(BuildingType.STORAGE, ResourceType.CORN, 3), 60);
                put(new BuildingResourceId(BuildingType.STORAGE, ResourceType.TIMBER, 3), 80);
                put(new BuildingResourceId(BuildingType.STORAGE, ResourceType.GOLD, 3), 40);

                put(new BuildingResourceId(BuildingType.TOWNHALL, ResourceType.CLAY, 1), 15);
                put(new BuildingResourceId(BuildingType.TOWNHALL, ResourceType.CORN, 1), 10);
                put(new BuildingResourceId(BuildingType.TOWNHALL, ResourceType.TIMBER, 1), 20);
                put(new BuildingResourceId(BuildingType.TOWNHALL, ResourceType.GOLD, 1), 25);

                put(new BuildingResourceId(BuildingType.TOWNHALL, ResourceType.CLAY, 2), 30);
                put(new BuildingResourceId(BuildingType.TOWNHALL, ResourceType.CORN, 2), 20);
                put(new BuildingResourceId(BuildingType.TOWNHALL, ResourceType.TIMBER, 2), 40);
                put(new BuildingResourceId(BuildingType.TOWNHALL, ResourceType.GOLD, 2), 50);

                put(new BuildingResourceId(BuildingType.TOWNHALL, ResourceType.CLAY, 3), 60);
                put(new BuildingResourceId(BuildingType.TOWNHALL, ResourceType.CORN, 3), 40);
                put(new BuildingResourceId(BuildingType.TOWNHALL, ResourceType.TIMBER, 3), 80);
                put(new BuildingResourceId(BuildingType.TOWNHALL, ResourceType.GOLD, 3), 100);
            };

        };

        TIME_BUILDINGS_INFO = new HashMap<TimeId, Integer>() {
            {
                put(new TimeId(BuildingType.BARN, 1), 5);
                put(new TimeId(BuildingType.BARN, 2), 10);
                put(new TimeId(BuildingType.BARN, 3), 15);

                put(new TimeId(BuildingType.FARM, 1), 5);
                put(new TimeId(BuildingType.FARM, 2), 10);
                put(new TimeId(BuildingType.FARM, 3), 15);

                put(new TimeId(BuildingType.GILOTHOME, 1), 5);
                put(new TimeId(BuildingType.GILOTHOME, 2), 10);
                put(new TimeId(BuildingType.GILOTHOME, 3), 15);

                put(new TimeId(BuildingType.MARKETPLACE, 1), 5);
                put(new TimeId(BuildingType.MARKETPLACE, 2), 10);
                put(new TimeId(BuildingType.MARKETPLACE, 3), 15);

                put(new TimeId(BuildingType.MALL, 1), 5);
                put(new TimeId(BuildingType.MALL, 2), 10);
                put(new TimeId(BuildingType.MALL, 3), 15);

                put(new TimeId(BuildingType.PLANT, 1), 5);
                put(new TimeId(BuildingType.PLANT, 2), 10);
                put(new TimeId(BuildingType.PLANT, 3), 15);

                put(new TimeId(BuildingType.POOL, 1), 5);
                put(new TimeId(BuildingType.POOL, 2), 10);
                put(new TimeId(BuildingType.POOL, 3), 15);

                put(new TimeId(BuildingType.SCHOOL, 1), 5);
                put(new TimeId(BuildingType.SCHOOL, 2), 10);
                put(new TimeId(BuildingType.SCHOOL, 3), 15);

                put(new TimeId(BuildingType.STORAGE, 1), 5);
                put(new TimeId(BuildingType.STORAGE, 2), 10);
                put(new TimeId(BuildingType.STORAGE, 3), 15);

                put(new TimeId(BuildingType.TOWNHALL, 1), 5);
                put(new TimeId(BuildingType.TOWNHALL, 2), 10);
                put(new TimeId(BuildingType.TOWNHALL, 3), 15);
            };
        };
    }
}

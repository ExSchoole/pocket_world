package org.exschool.pocketworld.resource.building.service;

import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.resource.building.model.*;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.exschool.pocketworld.util.builder.ResourceBuildingBuilder;
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
 * Created by manoylo on 12.11.15.
 */
@Service("resourceBuildingService")
@Transactional
public class ResourceBuildingServiceImpl implements ResourceBuildingService {
    /**
     * dao - data access object
     */
    @Autowired
    private Dao dao;

    private final Map<BuildingResourceId, Integer> RESOURCE_BUILDINGS_INFO;
    private final Map<TimeId, Integer> TIME_RESOURCE_BUILDINGS_INFO;
    private final Map<ProductionId, Integer> PRODUCTION_RESOURCE_BUILDINGS_INFO;

    @Override
    public Long createResourceBuilding(Long cityId, ResourceType resourceType, int position, int level) {
        ResourceBuilding resourceBuilding = ResourceBuildingBuilder.builder()
                .buildingType(resourceType)
                .cityId(cityId)
                .level(level)
                .position(position)
                .build();

        save(resourceBuilding);
        return resourceBuilding.getId();
    }

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
    public List<ResourceBuilding> allCityResources(Long id) {
        DetachedCriteria query = DetachedCriteria.forClass(ResourceBuilding.class);
        query.add(Restrictions.eq("cityId", id));
        return dao.getAllBy(query);
    }

    /**
     * Returns resource building for specific position and city or null if
     * the position is empty
     *
     * @param cityId   cityId
     * @param position position of resource building
     */
    @Override
    public ResourceBuilding getAtPosition(Long cityId, Integer position) {
        DetachedCriteria dc = DetachedCriteria.forClass(ResourceBuilding.class);
        dc.add(Restrictions.eq("cityId", cityId));
        dc.add(Restrictions.eq("position", position));
        return (ResourceBuilding) dao.getBy(dc);
    }

    @Override
    public boolean isResBuildingExist(Long cityId, int position) {
        return getAtPosition(cityId, position) != null;
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
            dao.save(new BuildingResource(b.getKey(), b.getValue()));

        for (Entry<TimeId, Integer> t : TIME_RESOURCE_BUILDINGS_INFO.entrySet())
            dao.save(new ResourceBuildingTime(t.getKey(), t.getValue()));

        for (Entry<ProductionId, Integer> p : PRODUCTION_RESOURCE_BUILDINGS_INFO.entrySet())
            dao.save(new Production(p.getKey(), p.getValue()));
    }

    @Override
    public int getProductionByBuildingTypeLevel(ResourceType buildingType, int level) {
        return PRODUCTION_RESOURCE_BUILDINGS_INFO.get(new ProductionId(buildingType, level));
    }

    @Override
    public int getTimeByBuildingTypeLevel(ResourceType buildingType, int level) {
        return TIME_RESOURCE_BUILDINGS_INFO.get(new TimeId(buildingType, level));
    }

    @Override
    public int getResourcesByBuildingTypeLevel(ResourceType buildingType, ResourceType resourceType, int level) {
        return RESOURCE_BUILDINGS_INFO.get(new BuildingResourceId(buildingType, resourceType, level));
    }

    @Override
    public Map<BuildingResourceId, Integer> getRESOURCE_BUILDINGS_INFO() {
        return RESOURCE_BUILDINGS_INFO;
    }

    @Override
    public Map<TimeId, Integer> getTIME_RESOURCE_BUILDINGS_INFO() {
        return TIME_RESOURCE_BUILDINGS_INFO;
    }

    @Override
    public Map<ProductionId, Integer> getPRODUCTION_RESOURCE_BUILDINGS_INFO() {
        return PRODUCTION_RESOURCE_BUILDINGS_INFO;
    }

    {

        RESOURCE_BUILDINGS_INFO = new HashMap<BuildingResourceId, Integer>() {
            {
                put(new BuildingResourceId(ResourceType.CLAY, ResourceType.CLAY, 1), 10);
                put(new BuildingResourceId(ResourceType.CLAY, ResourceType.CORN, 1), 15);
                put(new BuildingResourceId(ResourceType.CLAY, ResourceType.GOLD, 1), 20);
                put(new BuildingResourceId(ResourceType.CLAY, ResourceType.TIMBER, 1), 25);

                put(new BuildingResourceId(ResourceType.CLAY, ResourceType.CLAY, 2), 30);
                put(new BuildingResourceId(ResourceType.CLAY, ResourceType.CORN, 2), 35);
                put(new BuildingResourceId(ResourceType.CLAY, ResourceType.GOLD, 2), 40);
                put(new BuildingResourceId(ResourceType.CLAY, ResourceType.TIMBER, 2), 45);

                put(new BuildingResourceId(ResourceType.CLAY, ResourceType.CLAY, 3), 50);
                put(new BuildingResourceId(ResourceType.CLAY, ResourceType.CORN, 3), 55);
                put(new BuildingResourceId(ResourceType.CLAY, ResourceType.GOLD, 3), 60);
                put(new BuildingResourceId(ResourceType.CLAY, ResourceType.TIMBER, 3), 65);

                put(new BuildingResourceId(ResourceType.CORN, ResourceType.CLAY, 1), 25);
                put(new BuildingResourceId(ResourceType.CORN, ResourceType.CORN, 1), 20);
                put(new BuildingResourceId(ResourceType.CORN, ResourceType.GOLD, 1), 15);
                put(new BuildingResourceId(ResourceType.CORN, ResourceType.TIMBER, 1), 10);

                put(new BuildingResourceId(ResourceType.CORN, ResourceType.CLAY, 2), 45);
                put(new BuildingResourceId(ResourceType.CORN, ResourceType.CORN, 2), 40);
                put(new BuildingResourceId(ResourceType.CORN, ResourceType.GOLD, 2), 35);
                put(new BuildingResourceId(ResourceType.CORN, ResourceType.TIMBER, 2), 30);

                put(new BuildingResourceId(ResourceType.CORN, ResourceType.CLAY, 3), 65);
                put(new BuildingResourceId(ResourceType.CORN, ResourceType.CORN, 3), 60);
                put(new BuildingResourceId(ResourceType.CORN, ResourceType.GOLD, 3), 55);
                put(new BuildingResourceId(ResourceType.CORN, ResourceType.TIMBER, 3), 50);

                put(new BuildingResourceId(ResourceType.GOLD, ResourceType.CLAY, 1), 10);
                put(new BuildingResourceId(ResourceType.GOLD, ResourceType.CORN, 1), 20);
                put(new BuildingResourceId(ResourceType.GOLD, ResourceType.GOLD, 1), 30);
                put(new BuildingResourceId(ResourceType.GOLD, ResourceType.TIMBER, 1), 40);

                put(new BuildingResourceId(ResourceType.GOLD, ResourceType.CLAY, 2), 20);
                put(new BuildingResourceId(ResourceType.GOLD, ResourceType.CORN, 2), 40);
                put(new BuildingResourceId(ResourceType.GOLD, ResourceType.GOLD, 2), 60);
                put(new BuildingResourceId(ResourceType.GOLD, ResourceType.TIMBER, 2), 80);

                put(new BuildingResourceId(ResourceType.GOLD, ResourceType.CLAY, 3), 40);
                put(new BuildingResourceId(ResourceType.GOLD, ResourceType.CORN, 3), 80);
                put(new BuildingResourceId(ResourceType.GOLD, ResourceType.GOLD, 3), 120);
                put(new BuildingResourceId(ResourceType.GOLD, ResourceType.TIMBER, 3), 160);

                put(new BuildingResourceId(ResourceType.TIMBER, ResourceType.CLAY, 1), 10);
                put(new BuildingResourceId(ResourceType.TIMBER, ResourceType.CORN, 1), 10);
                put(new BuildingResourceId(ResourceType.TIMBER, ResourceType.GOLD, 1), 10);
                put(new BuildingResourceId(ResourceType.TIMBER, ResourceType.TIMBER, 1), 10);

                put(new BuildingResourceId(ResourceType.TIMBER, ResourceType.CLAY, 2), 30);
                put(new BuildingResourceId(ResourceType.TIMBER, ResourceType.CORN, 2), 30);
                put(new BuildingResourceId(ResourceType.TIMBER, ResourceType.GOLD, 2), 30);
                put(new BuildingResourceId(ResourceType.TIMBER, ResourceType.TIMBER, 2), 30);

                put(new BuildingResourceId(ResourceType.TIMBER, ResourceType.CLAY, 3), 50);
                put(new BuildingResourceId(ResourceType.TIMBER, ResourceType.CORN, 3), 50);
                put(new BuildingResourceId(ResourceType.TIMBER, ResourceType.GOLD, 3), 50);
                put(new BuildingResourceId(ResourceType.TIMBER, ResourceType.TIMBER, 3), 50);
            }

            ;
        };
      
        TIME_RESOURCE_BUILDINGS_INFO = new HashMap<TimeId, Integer>(){
        	{	  
        		put(new TimeId(ResourceType.CLAY,1),20);
        		put(new TimeId(ResourceType.CLAY,2),10);
        		put(new TimeId(ResourceType.CLAY,3),15);
        		
        		put(new TimeId(ResourceType.TIMBER,1),20);
        		put(new TimeId(ResourceType.TIMBER,2),10);
        		put(new TimeId(ResourceType.TIMBER,3),15);
        		
        		put(new TimeId(ResourceType.GOLD,1),20);
        		put(new TimeId(ResourceType.GOLD,2),10);
        		put(new TimeId(ResourceType.GOLD,3),15);
        		
        		put(new TimeId(ResourceType.CORN,1),20);
        		put(new TimeId(ResourceType.CORN,2),10);
        		put(new TimeId(ResourceType.CORN,3),15);
        	};
        };

        PRODUCTION_RESOURCE_BUILDINGS_INFO = new HashMap<ProductionId, Integer>() {
            {
                put(new ProductionId(ResourceType.CLAY, 1), 5);
                put(new ProductionId(ResourceType.CLAY, 2), 10);
                put(new ProductionId(ResourceType.CLAY, 3), 20);

                put(new ProductionId(ResourceType.CORN, 1), 5);
                put(new ProductionId(ResourceType.CORN, 2), 10);
                put(new ProductionId(ResourceType.CORN, 3), 20);

                put(new ProductionId(ResourceType.TIMBER, 1), 5);
                put(new ProductionId(ResourceType.TIMBER, 2), 10);
                put(new ProductionId(ResourceType.TIMBER, 3), 20);

                put(new ProductionId(ResourceType.GOLD, 1), 5);
                put(new ProductionId(ResourceType.GOLD, 2), 10);
                put(new ProductionId(ResourceType.GOLD, 3), 20);
            }

            ;
        };

    };

}

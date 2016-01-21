package org.exschool.pocketworld.resource.building.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.exschool.pocketworld.util.builder.ResourceBuildingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by manoylo on 20.11.15.
 */
@Service
@Transactional
public class ResourceBuildingBootstrap {
    @Autowired
    Dao dao;

    private List<ResourceBuilding> buildings;
    private volatile boolean bootstraped = false;

    public void fillDatabase() {
        if (bootstraped) return;
        buildings = new ArrayList<>();
        
        ResourceBuilding resourceBuilding1 = ResourceBuildingBuilder.builder().buildingType(ResourceType.GOLD).level(1).position(1).cityId(1L).build();
        buildings.add(resourceBuilding1);
        
        ResourceBuilding resourceBuilding2 = ResourceBuildingBuilder.builder().buildingType(ResourceType.TIMBER).level(1).position(2).cityId(2L).build();
        buildings.add(resourceBuilding2);
        
        ResourceBuilding resourceBuilding3 = ResourceBuildingBuilder.builder().buildingType(ResourceType.TIMBER).level(1).position(3).cityId(3L).build();
        buildings.add(resourceBuilding3);
        
        ResourceBuilding resourceBuilding4 = ResourceBuildingBuilder.builder().buildingType(ResourceType.CLAY).level(1).position(1).cityId(5L).build();
        buildings.add(resourceBuilding4);
        
        ResourceBuilding resourceBuilding5 = ResourceBuildingBuilder.builder().buildingType(ResourceType.TIMBER).level(3).position(2).cityId(5L).build();
        buildings.add(resourceBuilding5);
        
        ResourceBuilding resourceBuilding6 = ResourceBuildingBuilder.builder().buildingType(ResourceType.CORN).level(4).position(3).cityId(5L).build();
        buildings.add(resourceBuilding6);
        
        dao.saveAll(Arrays.asList(resourceBuilding1, resourceBuilding2, resourceBuilding3,resourceBuilding4,resourceBuilding5,resourceBuilding6));
        bootstraped = true;
    }
    
    public List<ResourceBuilding> getBuildings(){
        return buildings;
    }

}

package org.exschool.pocketworld.resource.building.service;

import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.exschool.pocketworld.util.builder.ResourceBuildingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * Created by manoylo on 20.11.15.
 */
@Service
@Transactional
public class ResourceBuildingBootstrap {
    @Autowired
    Dao dao;

    private volatile boolean bootstraped = false;

    public void fillDatabase() {
        if (bootstraped) return;
        ResourceBuilding resourceBuilding1 = ResourceBuildingBuilder.builder().buildingType(ResourceType.GOLD).level(1).position(1).cityId(1L).build();
        ResourceBuilding resourceBuilding2 = ResourceBuildingBuilder.builder().buildingType(ResourceType.TIMBER).level(1).position(2).cityId(1L).build();
        ResourceBuilding resourceBuilding3 = ResourceBuildingBuilder.builder().buildingType(ResourceType.TIMBER).level(1).position(3).cityId(1L).build();
        dao.saveAll(Arrays.asList(resourceBuilding1, resourceBuilding2, resourceBuilding3));
        bootstraped = true;
    }

}

package org.exschool.pocketworld.building.service;

import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.util.builder.BuildingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * Created by skandy on 18.11.15.
 */
@Service
@Transactional
public class BuildingBootstrap {
    @Autowired
    Dao dao;

    private volatile boolean bootstraped = false;

    public void fillDatabase() {
        if (bootstraped) return;
        Building building1 = BuildingBuilder.builder().buildingId(1L).buildingType(BuildingType.TOWNHALL).level(1).position(1).cityId(1L).build();
        Building building2 = BuildingBuilder.builder().buildingId(2L).buildingType(BuildingType.FARM).level(1).position(2).cityId(1L).build();
        Building building3 = BuildingBuilder.builder().buildingId(3L).buildingType(BuildingType.FARM).level(1).position(3).cityId(1L).build();
        dao.saveAll(Arrays.asList(building1,building2,building3));
        bootstraped = true;
    }

}

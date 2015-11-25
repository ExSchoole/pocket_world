package org.exschool.pocketworld.building;

import org.exschool.pocketworld.building.model.UserCity;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.util.builder.UserCityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Created by skandy on 18.11.15.
 */
@Service
@Transactional
public class UserCityBootstrap {
    @Autowired
    Dao dao;

    private volatile boolean bootstraped = false;

    public void fillDatabase() {
        if (bootstraped) return;
        List<Long> buildingsId_list  = new ArrayList<>();
        buildingsId_list.add(21L);
        buildingsId_list.add(22L);
        buildingsId_list.add(23L);

        List<Long>resourceBuildingsId_list = new ArrayList<>();
        resourceBuildingsId_list.add(21L);
        resourceBuildingsId_list.add(22L);
        resourceBuildingsId_list.add(23L);

        UserCity city1 = UserCityBuilder.builder().playerId(1L).buildingsId(buildingsId_list).name("City1").resourceBuildingsId(resourceBuildingsId_list).build();
        UserCity city2 = UserCityBuilder.builder().playerId(1L).buildingsId(buildingsId_list).name("City2").resourceBuildingsId(resourceBuildingsId_list).build();
        UserCity city3 = UserCityBuilder.builder().playerId(1L).buildingsId(buildingsId_list).name("City3").resourceBuildingsId(resourceBuildingsId_list).build();

        dao.saveAll(Arrays.asList(city1,city2,city3));
        bootstraped = true;
    }

}

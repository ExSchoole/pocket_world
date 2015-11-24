package org.exschool.pocketworld.building;

import org.exschool.pocketworld.building.model.UserCity;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.util.builder.UserCityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

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
        UserCity city1 = UserCityBuilder.builder().playerId(1L).buildingsId(2L).name("City1").resourceBuildingsId(3L).build();
        UserCity city2 = UserCityBuilder.builder().playerId(1L).buildingsId(2L).name("City2").resourceBuildingsId(3L).build();
        UserCity city3 = UserCityBuilder.builder().playerId(1L).buildingsId(2L).name("City3").resourceBuildingsId(3L).build();

        dao.saveAll(Arrays.asList(city1,city2,city3));
        bootstraped = true;
    }

}

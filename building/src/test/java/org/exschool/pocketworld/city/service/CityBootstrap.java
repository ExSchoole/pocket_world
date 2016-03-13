package org.exschool.pocketworld.city.service;

import org.exschool.pocketworld.city.model.City;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.util.builder.UserCityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * Created by skandy on 18.11.15.
 */
@Service
@Transactional
public class CityBootstrap {
    @Autowired
    Dao dao;

    final static List<Long> savedCitiesIds = new ArrayList<>();
    private List<City> allCities = new ArrayList<>();

    private volatile boolean bootstraped = false;

    public void fillDatabase() {
        if (bootstraped) return;

        City city1 = UserCityBuilder.builder().playerId(1L).name("City1").build();
        allCities.add(city1);

        City city2 = UserCityBuilder.builder().playerId(2L).name("City2").build();
        allCities.add(city2);

        City city3 = UserCityBuilder.builder().playerId(3L).name("City3").build();
        allCities.add(city3);

        Collection<City> savedCities = dao.saveAll(Arrays.asList(city1, city2, city3));
        for (City city : savedCities) {
            savedCitiesIds.add(city.getId());
        }
        bootstraped = true;
    }

    public List<City> getAllCities(){
        return allCities;
    }

}

package org.exschool.pocketworld.city.service;

import org.exschool.pocketworld.city.model.City;

import java.util.List;

/**
 * Created by manoylo with baditsa on 18.11.15.
 */
public interface CityService {
    List<City> allCities();

    City get(long id);

    City save(City entity);

    City getCityByPlayerId(Long playerId);

    Long getCityId(Long playerId);

    Boolean isCityExist(Long playerId);

    Long createCity(Long playerId, String cityName);
}


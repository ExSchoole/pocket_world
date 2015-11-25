package org.exschool.pocketworld.building.service;

import org.exschool.pocketworld.building.model.UserCity;

import java.util.List;

/**
 * Created by manoylo with baditsa on 18.11.15.
 */
public interface UserCityService {
    List<UserCity> allCities();
    UserCity get (long id);
    UserCity  save(UserCity entity);
    UserCity getCityByPlayerId(Long id);

}


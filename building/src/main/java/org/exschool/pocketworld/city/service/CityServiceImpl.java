package org.exschool.pocketworld.city.service;

import org.exschool.pocketworld.city.model.City;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.util.builder.UserCityBuilder;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by manoylo with baditsa on 18.11.15.
 */


@Service("userCityService")
@Transactional
public class CityServiceImpl implements CityService {
    /**
     * dao - data access object
     */
    @Autowired
    private Dao dao;

    /**
     * Gets all buildings from DB
     *
     * @return list of buildings
     */

    @Override
    public Long createCity(Long playerId, String cityName) {
        City city = UserCityBuilder.builder()
                .name(cityName)
                .playerId(playerId)
                .build();
        save(city);
        return city.getId();
    }

    @Override
    public List<City> allCities() {
        return dao.all(City.class);
    }

    /**
     * Returns building by id
     *
     * @param id
     * @return Building object
     */
    @Override
    public City get(long id) {
        return dao.get(City.class, id);
    }

    /**
     * Saves entity object to DB
     *
     * @param entity
     */
    @Override
    public City save(City entity) {
        return dao.save(entity);

    }

    /**
     * Get player's city
     *
     * @param playerId
     * @return City object
     */
    @Override
    public City getCityByPlayerId(Long playerId) {
        DetachedCriteria query = DetachedCriteria.forClass(City.class);
        query.add(Restrictions.eq("playerId", playerId));
        return dao.getBy(query);
    }

    /**
     * Get city's id
     *
     * @param playerId
     * @return City Id
     */
    @Override
    public Long getCityId(Long playerId) {
        return getCityByPlayerId(playerId) != null ?
                getCityByPlayerId(playerId).getId() :
                null;
    }

    /**
     * Verify whether city id exist for a player
     *
     * @param playerId
     * @return Boolean
     */
    @Override
    public Boolean isCityExist(Long playerId) {
        return getCityByPlayerId(playerId) != null;
    }

    /**
     * Setter for dao
     *
     * @param dao
     */
    public void setDao(Dao dao) {
        this.dao = dao;
    }

}

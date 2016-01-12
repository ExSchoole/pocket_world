package org.exschool.pocketworld.city.service;

import org.exschool.pocketworld.city.model.City;
import org.exschool.pocketworld.dao.Dao;
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
     * @return list of buildings
     */
    @Override
    public List<City> allCities() {
        return dao.all(City.class);
    }

    /**
     * Returns building by id
     * @param id
     * @return Building object
     */
    @Override
    public City get(long id) {
        return dao.get(City.class, id);
    }

    /**
     * Saves entity object to DB
     * @param entity
     */
    @Override
    public City save(City entity) {
        return dao.save(entity);

    }

    @Override
    public City getCityByPlayerId(Long playerId) {
        DetachedCriteria query = DetachedCriteria.forClass(City.class);
        query.add(Restrictions.eq("playerId", playerId));
        return dao.getBy(query);
    }


    /**
     * Setter for dao
     * @param dao
     */
    public void setDao(Dao dao) {
        this.dao = dao;
    }
}

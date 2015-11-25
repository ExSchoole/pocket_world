package org.exschool.pocketworld.building.service;
//import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.building.model.UserCity;
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
public class UserCityServiceImpl implements UserCityService {
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
    public List<UserCity> allCities() {
        return dao.all(UserCity.class);
    }

    /**
     * Returns building by id
     * @param id
     * @return Building object
     */
    @Override
    public UserCity get(long id) {
        return dao.get(UserCity.class, id);
    }

    /**
     * Saves entity object to DB
     * @param entity
     */
    @Override
    public UserCity save(UserCity entity) {
        return dao.save(entity);

    }

    /**
     * Setter for dao
     * @param dao
     */
    public void setDao(Dao dao) {
        this.dao = dao;
    }


    public UserCity getCityByPlayerId(Long id) {
        DetachedCriteria query = DetachedCriteria.forClass(UserCity.class);
        query.add(Restrictions.eq("id", id));
        return dao.getBy(query);
    }
}

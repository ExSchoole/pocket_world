package org.exschool.pocketworld.building.service;

import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


/**
 * Created by skandy on 12.11.15.
 */
@Service("buildingService")
@Transactional
public class BuildingServiceImpl  implements BuildingService {
    @Autowired
    private Dao dao;

    @Override
    public List<Building> allBuildings() {
        return dao.all(Building.class);
    }

    @Override
    public Building get(long id) {
        return dao.get(Building.class, id);
    }

    @Override
    public void save(Building entity) {
        dao.save(entity);

    }
}

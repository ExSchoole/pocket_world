package org.exschool.pocketworld.info.building;

import java.util.Collection;
import java.util.List;

import org.exschool.pocketworld.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("buildingInfoService")
@Transactional
public class BuildingInfoServiceImpl implements BuildingInfoService {

	@Autowired
	private Dao dao;
	
	@Override
	public List<BuildingInfo> allBuildings() {	
		return dao.all(BuildingInfo.class);
	}

	@Override
	public Collection<BuildingInfo> saveAll() {
		return dao.saveAll(BUILDINGS_INFO);
	}

	public void setDao(Dao dao) {
        this.dao = dao;
    }
}

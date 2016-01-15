package org.exschool.pocketworld.info.resourcebuilding;

import java.util.Collection;
import java.util.List;

import org.exschool.pocketworld.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("resourceBuildingInfoService")
@Transactional
public class ResourceBuildingInfoServiceImpl implements ResourceBuildingInfoService {

	@Autowired
	private Dao dao;
	
	@Override
	public List<ResourceBuildingInfo> allBuildings() {
		return dao.all(ResourceBuildingInfo.class);
	}

	@Override
	public Collection<ResourceBuildingInfo> saveAll() {
		return dao.saveAll(RESOURCE_BUILDINGS_INFO);
	}

	public void setDao(Dao dao) {
        this.dao = dao;
    }
}

package org.exschool.pocketworld.resource.service;

import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.resource.model.Resources;

public class ResourceServiceImpl implements ResourceService {
	private Dao dao;
	@Override
	public void saveResources(Resources resources) {
		dao.save(resources);
		
	}

	@Override
	public Resources getResourcesById(Long id) {
		
		return dao.get(Resources.class, id);
	}

}

package org.exschool.pocketworld.resource.service;

import java.util.List;

import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.resource.model.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("Resource")
@Transactional
public class ResourcesServiceImpl implements ResourcesService {
	/**
     * dao - data access object
     */
    @Autowired
	private Dao dao;
	
	@Override
	public Resource saveResources(Resource resources) {
		return dao.save(resources);
		
	}

	@Override
	public List<Resource> getAllResourcesByUserId(Long id) {
		DetachedCriteria query = DetachedCriteria.forClass(Resource.class);
        query.add(Restrictions.eq("playerId", id));
        return dao.getAllBy(query);
	}

	public Resource getResourcesByUserId(Long id) {
		DetachedCriteria query = DetachedCriteria.forClass(Resource.class);
        query.add(Restrictions.eq("playerId", id));
        return dao.getBy(query);
	}
	
	public void saveAllResources(List<Resource> resources){
		dao.saveAll(resources);
	}
}

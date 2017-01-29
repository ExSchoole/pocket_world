package org.exschool.pocketworld.service;

import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.model.Book;
import org.exschool.pocketworld.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cityService")
@Transactional
public class CityServiceImpl implements CityService {

	@Autowired
	private Dao dao;

	@Override
	public City get(final long id) {
		return dao.get(City.class,id);
	}

	@Override
	public void save(final City entity) {
		dao.save(entity);
		
	}

}

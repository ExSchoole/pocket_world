package org.exschool.pocketworld.service;

import org.exschool.pocketworld.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cityService")
@Transactional
public class CityService {

	@Autowired
	private DAO Dao;

}

package org.exschool.pocketworld.service;

import org.exschool.pocketworld.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("productionService")
@Transactional
public class ProductionService {

	@Autowired
	private DAO Dao;

}

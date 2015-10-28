package org.exschool.pocketworld.service;

import org.exschool.pocketworld.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bildingService")
@Transactional
public class BildingService implements Service {
	
	@Autowired
	private DAO Dao;

}

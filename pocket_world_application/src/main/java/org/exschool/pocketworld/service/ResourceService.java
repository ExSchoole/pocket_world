package org.exschool.pocketworld.service;


import org.exschool.pocketworld.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("resourceService")
@Transactional
public class ResourceService {

	@Autowired
	private Dao dao;

}

package org.exschool.pocketworld.resource.service;

import java.util.Arrays;

import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.resource.builder.ResourcesBuider;
import org.exschool.pocketworld.resource.model.Resource;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by manoylo on 20.11.15.
 */
@Service
@Transactional
public class ResourcesBootstrap {
	@Autowired
    Dao dao;

    private volatile boolean bootstraped = false;

    public void fillDatabase() {
        if (bootstraped) return;
        Resource resources1 = ResourcesBuider.builder().resourceType(ResourceType.GOLD).playerId(2L).amount(10).build();
        Resource resources2 = ResourcesBuider.builder().resourceType(ResourceType.TIMBER).playerId(2L).amount(20).build();
        Resource resources3 = ResourcesBuider.builder().resourceType(ResourceType.CLAY).playerId(2L).amount(30).build();
        Resource resources4 = ResourcesBuider.builder().resourceType(ResourceType.CORN).playerId(2L).amount(40).build();
        
        
        
        dao.saveAll(Arrays.asList(resources1, resources2, resources3,resources4));
        bootstraped = true;
    }
}

package org.exschool.pocketworld.resource.service;

import java.util.ArrayList;
import java.util.List;

import org.exschool.pocketworld.config.TestSpringConfig;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.resource.builder.ResourcesBuider;
import org.exschool.pocketworld.resource.model.Resource;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestSpringConfig.class)
public class ResourcesServiceTest {

    @Autowired
    ResourcesService resourcesService;
    @Autowired
    ResourcesBootstrap bootstrap;
    @Autowired
    Dao dao;

    @Before
    public void before() {
        bootstrap.fillDatabase();
    }

    @Test
    public void testCreate() {
       
    	Resource resource= new Resource();
    	 resource = ResourcesBuider.builder().resourceType(ResourceType.GOLD).playerId(1L).amount(10).build();
    	 
    	 List<Resource> savedResources= new   ArrayList<>();
    	 savedResources.add(resource);
    	 savedResources.add(resourcesService.saveResources(resource));
    	 assertNotNull(savedResources);
    	 assertNotNull(savedResources.get(0).getPlayerId());
    }

    @Test
    public void testUpdate() {
        Long existingPlayerId = 2L;
        List<Resource> existingResource= resourcesService.getAllResourcesByUserId(existingPlayerId);
        assertNotNull(existingResource);
        existingResource.get(0).setAmount(100);
        existingResource.get(1).setAmount(100);
        existingResource.get(2).setAmount(100);
        existingResource.get(3).setAmount(100);
        resourcesService.saveAllResources(existingResource);
        
        List<Resource> savedResource = resourcesService.getAllResourcesByUserId(existingPlayerId);
        assertFalse(existingResource.equals(savedResource));
    }

    @Test(expected = Exception.class)
    public void testSaveNull() {
    	resourcesService.saveAllResources(null);
    }


   @Test
    public void getAllResourcesByUserId() {
	   List<Resource> existingResource= resourcesService.getAllResourcesByUserId(2L);
        assertNotNull(existingResource);
    }
}



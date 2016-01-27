package org.exschool.pocketworld.resource.building.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.exschool.pocketworld.config.TestSpringConfig;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.exschool.pocketworld.util.builder.ResourceBuildingBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
/**
 * Created by manoylo on 20.11.15.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestSpringConfig.class)
public class ResourceBuildingServiceTest {
    @Autowired
    ResourceBuildingService buildingService;
    @Autowired
    ResourceBuildingBootstrap bootstrap;
    @Autowired
    Dao dao;

    @Before
    public void before() {
        bootstrap.fillDatabase();
        buildingService.saveAllInformation();
    }

    @Test
    public void testCreate() {
        ResourceBuilding building = ResourceBuildingBuilder.builder().build();
        ResourceBuilding savedBuilding = buildingService.save(building);
        assertNotNull(savedBuilding);
        assertNotNull(savedBuilding.getId());
        assertAllFieldsEquals(building, savedBuilding);
    }

    @Test
    public void testAllBuildings() {
        assertEquals(bootstrap.getBuildings(),buildingService.allBuildings());
    }
    
    @Test
    public void testUpdate() {
        Long existingBuildingId = 1L;
        ResourceBuilding existingBuilding = buildingService.get(existingBuildingId);
        assertNotNull(existingBuilding);
        existingBuilding.setCityId(10L);
        buildingService.save(existingBuilding);
        ResourceBuilding savedBuilding = buildingService.get(existingBuildingId);
        assertAllFieldsEquals(existingBuilding,savedBuilding);

    }

    @Test(expected = Exception.class)
    public void testSaveNull() {
        buildingService.save(null);
    }

    @Test
    public void testGetById() {
        ResourceBuilding existingBuilding = buildingService.get(3L);
        assertNotNull(existingBuilding);
    }
    @Test
    public void allCityBuildings() {
        List<ResourceBuilding> existingBuildings = buildingService.allCityResources(5L);
        assertNotNull(existingBuildings);
        assertNotNull(existingBuildings.get(0));
    }
    
    @Test
    public void testGetResourcesByBuildingTypeLevel(){
    	assertNotNull(buildingService.getResourcesByBuildingTypeLevel(ResourceType.CLAY, ResourceType.CORN, 1));
    }
    
    @Test
    public void testGetTimeByBuildingTypeLevel(){
    	assertNotNull(buildingService.getTimeByBuildingTypeLevel(ResourceType.GOLD, 2));
    }
    
    @Test
    public void testGetProductionByBuildingTypeLevel(){
    	assertNotNull(buildingService.getProductionByBuildingTypeLevel(ResourceType.TIMBER, 3));
    }

    private void assertAllFieldsEquals(ResourceBuilding building1, ResourceBuilding building2)
    {
        assertEquals(building2.getId(), building1.getId());
        assertEquals(building2.getResourceType(), building1.getResourceType());
        assertEquals(building2.getLevel(), building1.getLevel());
        assertEquals(building2.getPosition(), building1.getPosition());
        assertEquals(building2.getCityId(), building1.getCityId());

    }
     


}

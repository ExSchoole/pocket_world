package org.exschool.pocketworld.resource.building.service;

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

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

/**
 * Created by manoylo on 20.11.15.
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

    }
    @Test
    public void testCreateResourceBuilding() {
        Long cityId = 11L;
        int position = 55;
        int level = 49;
        Long createdResourceBuilding = buildingService.createResourceBuilding(cityId, ResourceType.CLAY,position,level);
        assertNotNull(createdResourceBuilding);
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
    public void testUpdate() {
        Long existingBuildingId = 1L;
        ResourceBuilding existingBuilding = buildingService.get(existingBuildingId);
        assertNotNull(existingBuilding);
        existingBuilding.setCityId(10L);
        buildingService.save(existingBuilding);
        ResourceBuilding savedBuilding = buildingService.get(existingBuildingId);
        assertAllFieldsEquals(existingBuilding, savedBuilding);

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
        List<ResourceBuilding> existingBuildings = buildingService.allCityBuildings(5L);
        assertNotNull(existingBuildings);
        assertNotNull(existingBuildings.get(0));
    }

    @Test
    public void testGetAtPositionIfBuildingExist() {
        Integer position = 1;
        Long cityId = 1L;
        Long buildingIdAtThePosition = 1L;
        ResourceBuilding resourceBuilding = buildingService.getAtPosition(cityId, position);
        assertNotNull(resourceBuilding);
        assertEquals(buildingIdAtThePosition, resourceBuilding.getId());
        assertEquals(cityId, resourceBuilding.getCityId());
    }

    @Test
    public void givenResBuildingExistAtPosition_isResBuildingExistReturnsTrue() {
        Integer position = 1;
        Long cityId = 1L;
        Boolean result = buildingService.isResBuildingExist(cityId, position);
        assertTrue(result);
    }

    @Test
    public void givenResBuildingDoesntExistAtPosition_isResBuildingExistReturnsFalse() {
        Integer position = 12;
        Long cityId = 1L;
        Boolean result = buildingService.isResBuildingExist(cityId, position);
        assertFalse(result);
    }

    @Test
    public void testGetAtPositionIfBuildingDoesntExist() {
        Integer position = 12;
        Long cityId = 1L;
        ResourceBuilding resourceBuilding = buildingService.getAtPosition(cityId, position);
        assertNull(resourceBuilding);
    }

    @Test
    public void testGetAtProperPositionButInDifferentCity() {
        Integer position = 1;
        Long notPropperCityId = 2L;
        ResourceBuilding resourceBuilding = buildingService.getAtPosition(notPropperCityId,
                position);
        assertNull(resourceBuilding);
    }

    private void assertAllFieldsEquals(ResourceBuilding building1, ResourceBuilding building2) {
        assertEquals(building2.getId(), building1.getId());
        assertEquals(building2.getResourceType(), building1.getResourceType());
        assertEquals(building2.getLevel(), building1.getLevel());
        assertEquals(building2.getPosition(), building1.getPosition());
        assertEquals(building2.getCityId(), building1.getCityId());

    }


}

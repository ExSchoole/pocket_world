package org.exschool.pocketworld.building.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.config.TestSpringConfig;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.exschool.pocketworld.util.builder.BuildingBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestSpringConfig.class)
public class BuildingServiceTest {
    @Autowired
    BuildingService buildingService;
    @Autowired
    BuildingBootstrap bootstrap;
    @Autowired
    Dao dao;

    @Before
    public void before() {
        bootstrap.fillDatabase();
        buildingService.saveAllInformation();

    }

    @Test
    public void testCreateBuildingForPopulator() {
        Long cityId= 111L;
        int position = 22;
        int level = 33;
        Long createBuilding = buildingService.createBuilding(cityId,BuildingType.FARM,position,level);
        assertNotNull(createBuilding);
    }

    @Test
    public void testCreate() {
        Building building = BuildingBuilder.builder().build();
        Building savedBuilding = buildingService.save(building);
        assertNotNull(savedBuilding);
        assertNotNull(savedBuilding.getId());
        assertAllFieldsEquals(building, savedBuilding);
    }

    @Test
    public void testUpdate() {
        Long existingBuildingId = 1L;
        Building existingBuilding = buildingService.get(existingBuildingId);
        assertNotNull(existingBuilding);
        existingBuilding.setCityId(10L);
        buildingService.save(existingBuilding);
        Building savedBuilding = buildingService.get(existingBuildingId);
        assertAllFieldsEquals(existingBuilding, savedBuilding);
    }

    @Test(expected = Exception.class)
    public void testSaveNull() {
        buildingService.save(null);
    }

    @Test
    public void testAllBuildings() {
        List<Building> buildings = buildingService.allBuildings();
        assertNotNull(buildings);
        assertFalse(buildings.size() == 0);
    }

    @Test
    public void testGetBuildingsByCityId(){
        Long cityId = 2L;
        List<Building> buildings = new ArrayList<>();
        for (Building b : bootstrap.getBuildings()){
            if (b.getCityId()==cityId) buildings.add(b);
        }

        assertEquals(buildings,buildingService.getBuildingsByCityId(cityId));
    }

    @Test
    public void testGetById() {
        Building existingBuilding = buildingService.get(3L);
        assertNotNull(existingBuilding);
    }

    @Test
    public void testGetResourceByBuildingTypeResourceTypeLevel(){
    	assertNotNull(buildingService.getResourceByBuildingTypeResourceTypeLevel(BuildingType.BARN, ResourceType.CLAY, 1));
    }

    @Test
    public void testGetTimeByBuildingTypeLevel(){
    	assertNotNull(buildingService.getTimeByBuildingTypeLevel(BuildingType.MALL, 2));
    }


    @Test
    public void testGetAtPositionIfBuildingExist() {
        Integer position = 1;
        Long cityId = 1L;
        Long buildingIdAtThePosition = 1L;
        Building building = buildingService.getAtPosition(cityId, position);
        assertNotNull(building);
        assertEquals(buildingIdAtThePosition, building.getId());
        assertEquals(cityId, building.getCityId());
    }

    @Test
    public void testGetAtPositionIfBuildingDoesntExist() {
        Integer position = 12;
        Long cityId = 1L;
        Building building = buildingService.getAtPosition(cityId, position);
        assertNull(building);
    }

    @Test
    public void testGetAtProperPositionButInDifferentCity() {
        Integer position = 1;
        Long nonExistingCityId = 2L;
        Building building = buildingService.getAtPosition(nonExistingCityId,
                position);
        assertNull(building);
    }

    @Test
    public void givenBuildingExistInCityAtPosition_isBuildingExistReturnsTrue() {
        Integer position = 1;
        Long cityId = 1L;
        Boolean result = buildingService.isBuildingExist(cityId, position);
        assertTrue(result);
    }

    @Test
    public void givenBuildingDoesntExistInCityAtPosition_isBuildingExistReturnsFalse() {
        Integer position = 12;
        Long cityId = 1L;
        Boolean result = buildingService.isBuildingExist(cityId, position);
        assertFalse(result);
    }

    private void assertAllFieldsEquals(Building building1, Building building2) {
        assertEquals(building2.getId(), building1.getId());
        assertEquals(building2.getBuildingType(), building1.getBuildingType());
        assertEquals(building2.getLevel(), building1.getLevel());
        assertEquals(building2.getPosition(), building1.getPosition());
        assertEquals(building2.getCityId(), building1.getCityId());
    }
}

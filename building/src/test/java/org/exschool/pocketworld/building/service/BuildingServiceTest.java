package org.exschool.pocketworld.building.service;

import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.config.TestSpringConfig;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.util.builder.BuildingBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        assertEquals(bootstrap.getBuildings(),buildingService.allBuildings());
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
    public void testSetterGetterDao(){
        BuildingServiceImpl b = new BuildingServiceImpl();
        b.setDao(dao);
        assertNotNull(b.getDao());
    }

    @Test
    public void testGetById() {
        Building existingBuilding = buildingService.get(3L);
        assertNotNull(existingBuilding);
    }

    private void assertAllFieldsEquals(Building building1, Building building2) {
        assertEquals(building2.getId(), building1.getId());
        assertEquals(building2.getBuildingType(), building1.getBuildingType());
        assertEquals(building2.getLevel(), building1.getLevel());
        assertEquals(building2.getPosition(), building1.getPosition());
        assertEquals(building2.getCityId(), building1.getCityId());
    }
}

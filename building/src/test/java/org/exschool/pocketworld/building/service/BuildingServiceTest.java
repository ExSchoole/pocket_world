package org.exschool.pocketworld.building.service;

import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.util.builder.BuildingBuilder;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.exschool.pocketworld.config.TestSpringConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestSpringConfig.class)
public class BuildingServiceTest {
    @Autowired
    BuildingService buildingService;
    @Autowired
    BuildingBootstrap bootstrap;
    @Autowired
    Dao dao;

    @Before
    public void before()
    {
        bootstrap.fillDatabase();

    }

    @Test
    public void testCreate()
    {
        Long biuldingId= 5L;
        Building building = BuildingBuilder.builder().buildingId(biuldingId).build();
        buildingService.save(building);
        Building savedBuilding = buildingService.get(biuldingId);
        assertNotNull(savedBuilding);
        assertNotNull(savedBuilding.getId());


    }

    @Test
    public void testUpdate() {
        Long existingBuildingId = 1L;
        Building existingBuilding = buildingService.get(existingBuildingId);
        assertNotNull(existingBuilding);
        existingBuilding.setCityId(10L);
        buildingService.save(existingBuilding);
        Building savedBuilding = buildingService.get(existingBuildingId);
        assertTrue(savedBuilding.equals(existingBuilding));

    }

    @Test(expected = Exception.class)
    public void testSaveNull() {
        buildingService.save(null);
    }

    @Test
    public void testGetById() {
        Building existingBuilding = buildingService.get(3L);
        assertNotNull(existingBuilding);
    }


}

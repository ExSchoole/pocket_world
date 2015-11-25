package org.exschool.pocketworld.building;

import org.exschool.pocketworld.building.model.UserCity;


import org.exschool.pocketworld.building.service.UserCityService;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.util.builder.UserCityBuilder;
import org.h2.engine.User;
import org.junit.*;
import org.junit.runner.RunWith;
import org.exschool.pocketworld.config.TestSpringConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestSpringConfig.class)

public class UserCityServiceTest {
    @Autowired
    UserCityService userCityService;
    @Autowired
    UserCityBootstrap bootstrap;
    @Autowired
    Dao dao;

    @Before
    public void before() {
        bootstrap.fillDatabase();

    }

    @Test
    public void testCreate() {
        UserCity city = UserCityBuilder.builder().build();
        UserCity savedCity = userCityService.save(city);
        assertNotNull(savedCity);
        assertNotNull(savedCity.getId());
        assertAllFieldsEquals(city,savedCity);
    }

    @Test
    public void testUpdate() {
        Long existingUserCityId = 1L;
        UserCity existingUserCity = userCityService.get(existingUserCityId);
        assertNotNull(existingUserCity);
        existingUserCity.setPlayerId(10L);
         userCityService.save(existingUserCity);
        UserCity savedCity = userCityService.get(existingUserCityId);
        assertAllFieldsEquals(existingUserCity,savedCity);

    }

    @Test(expected = Exception.class)
    public void testSaveNull() {
        userCityService.save(null);
    }

    @Test
    public void testGetById() {
        UserCity existingBuilding = userCityService.get(3L);
        assertNotNull(existingBuilding);
    }

    private void assertAllFieldsEquals(UserCity city1, UserCity city2)
    {
        assertEquals(city2.getId(), city1.getId());
        assertEquals(city2.getName(), city1.getName());
        assertEquals(city2.getBuildingsId(), city1.getBuildingsId());
        assertEquals(city2.getResourceBuildingsId(), city1.getResourceBuildingsId());
        assertEquals(city2.getPlayerId(), city1.getPlayerId());

    }



}

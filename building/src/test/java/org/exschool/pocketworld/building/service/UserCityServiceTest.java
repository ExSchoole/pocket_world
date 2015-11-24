package org.exschool.pocketworld.building;

import org.exschool.pocketworld.building.model.UserCity;


import org.exschool.pocketworld.building.service.UserCityService;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.util.builder.UserCityBuilder;
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
    public void before()
    {
        bootstrap.fillDatabase();

    }

    @Test
    public void testCreate()
    {
        Long cityId= 1L;
        UserCity city = UserCityBuilder.builder().build();
        //UserCity savedUserCity = userCityService.save(city);//
        //buildingService.save(building);
        userCityService.save(city);

        UserCity savedCities = userCityService.get(cityId);
        assertNotNull(savedCities);
        assertNotNull(savedCities.getId());

    }

    @Test
    public void testUpdate() {
        Long existingCityId = 1L;
        UserCity existingCity = userCityService.get(existingCityId);
        assertNotNull(existingCity);
        existingCity.setPlayerId(10L);// .setCityId(10L);
        userCityService.save(existingCity);
        UserCity savedCity = userCityService.get(existingCityId);
        assertTrue(savedCity.equals(existingCity));
    }

    @Test(expected = Exception.class)
    public void testSaveNull() {
        userCityService.save(null);
    }

    @Test
    public void testGetById() {
        UserCity existingCity= userCityService.get(3L);
        assertNotNull(existingCity);
    }
}

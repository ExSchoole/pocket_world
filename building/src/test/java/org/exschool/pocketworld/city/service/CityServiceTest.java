package org.exschool.pocketworld.city.service;

import org.exschool.pocketworld.city.model.City;
import org.exschool.pocketworld.config.TestSpringConfig;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.util.builder.UserCityBuilder;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestSpringConfig.class)
public class CityServiceTest {
    @Autowired
    CityService cityService;
    @Autowired
    CityBootstrap bootstrap;
    @Autowired
    Dao dao;

    @Before
    public void before() {
        bootstrap.fillDatabase();
    }

    @AfterClass
    public static void afterClass() {
        CityBootstrap.savedCitiesIds.clear();
    }

    @Test
    public void testCreate() {
        City city = UserCityBuilder.builder().build();
        City savedCity = cityService.save(city);
        assertNotNull(savedCity);
        assertNotNull(savedCity.getId());
        assertAllFieldsEquals(city, savedCity);
    }

    @Test
    public void testUpdate() {
        Long existingUserCityId = CityBootstrap.savedCitiesIds.get(0);
        City existingCity = cityService.get(existingUserCityId);
        assertNotNull(existingCity);
        existingCity.setPlayerId(10L);
        cityService.save(existingCity);
        City savedCity = cityService.get(existingUserCityId);
        assertAllFieldsEquals(existingCity, savedCity);
    }

    @Test(expected = Exception.class)
    public void testSaveNull() {
        cityService.save(null);
    }

    @Test
    public void testGetById() {
        Long id = CityBootstrap.savedCitiesIds.get(0);
        City existingBuilding = cityService.get(id);
        assertNotNull(existingBuilding);
    }

    private void assertAllFieldsEquals(City city1, City city2) {
        assertEquals(city2.getId(), city1.getId());
        assertEquals(city2.getName(), city1.getName());
        assertEquals(city2.getPlayerId(), city1.getPlayerId());
    }
}

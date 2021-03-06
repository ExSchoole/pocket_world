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

import java.util.List;

import static junit.framework.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
    public void testCreateCityForPopulator() {
        String cityName = "City111";
        Long playerId = 111L;
        cityService.createCity(playerId, cityName);
        Long createdCityId = cityService.getCityId(playerId);
        assertNotNull(createdCityId);
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

    @Test
    public void givenCityExistForPlayer_GetCityIdReturnsCityId() {
        Long playerId = 1L;
        Long expectedCityId = CityBootstrap.savedCitiesIds.get(0);
        Long actualCityId = cityService.getCityId(playerId);
        assertEquals(expectedCityId, actualCityId);
    }

    @Test
    public void givenCityDoesntExistForPlayer_GetCityIdReturnsNull() {
        Long playerId = 100L;
        assertNull(cityService.getCityId(playerId));
    }

    @Test
    public void givenCityExistForPlayer_IsCityExistReturnsTrue() {
        Long playerId = 1L;
        assertTrue(cityService.isCityExist(playerId));
    }

    @Test
    public void givenCityDoesntExistForPlayer_IsCityExistReturnsFalse() {
        Long playerId = 100L;
        assertFalse(cityService.isCityExist(playerId));
    }

    private void assertAllFieldsEquals(City city1, City city2) {
        assertEquals(city2.getId(), city1.getId());
        assertEquals(city2.getName(), city1.getName());
        assertEquals(city2.getPlayerId(), city1.getPlayerId());
    }

    @Test
    public void testAllCities() {
        List<City> cities = cityService.allCities();
        assertNotNull(cities);
        assertFalse(cities.size() == 0);
    }

    @Test
    public void testGetPlayerByCityId() {
        Long playerId = 1L;
        assertNotNull(cityService.getCityByPlayerId(playerId));
    }
}

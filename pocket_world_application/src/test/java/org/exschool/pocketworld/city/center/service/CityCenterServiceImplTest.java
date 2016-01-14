package org.exschool.pocketworld.city.center.service;

import com.google.common.collect.Sets;
import org.exschool.pocketworld.build.queue.service.BuildQueueService;
import org.exschool.pocketworld.building.BuildingDto;
import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.building.service.BuildingService;
import org.exschool.pocketworld.city.center.builder.CityCenterDtoBuilder;
import org.exschool.pocketworld.city.center.dto.CityCenterDto;
import org.exschool.pocketworld.city.model.City;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.model.PlayerResources;
import org.exschool.pocketworld.player.service.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.exschool.pocketworld.building.model.BuildingType.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityCenterServiceImplTest {

    @InjectMocks
    CityCenterService cityCenterService = new CityCenterServiceImpl();

    @Mock
    CityService cityService;
    @Mock
    PlayerService playerService;
    @Mock
    BuildingService buildingService;
    @Mock
    BuildQueueService buildQueueService;

    List<Building> buildings;
    PlayerResources playerResources;

    String playerName = "player-test";

    @Before
    public void before() {
        playerResources = new PlayerResources(100, 100, 100, 100);
        Player player = new Player(playerResources, playerName);
        player.setId(1L);
        City city = new City(player.getId(), "test-city");
        city.setId(1L);

        buildings = new ArrayList<>();
        buildings.add(new Building(MALL, 1, 1, city.getId()));
        buildings.add(new Building(PLANT, 1, 3, city.getId()));
        buildings.add(new Building(MARKETPLACE, 1, 6, city.getId()));
        buildings.add(new Building(POOL, 1, 9, city.getId()));

        Building building = new Building(BARN,1,7,city.getId());
        building.setId(1L);

        when(playerService.getPlayerByLogin(anyString())).thenReturn(player);
        when(cityService.getCityByPlayerId(anyLong())).thenReturn(city);
        when(buildingService.getBuildingsByCityId(anyLong())).thenReturn(buildings);
        when(buildingService.save(any(Building.class))).thenReturn(building);
    }


    @Test
    public void testAddBuilding() {
        assertTrue(cityCenterService.addBuilding(playerName, MALL.name().toLowerCase(), 4));
        assertFalse(cityCenterService.addBuilding(playerName, MALL.name().toLowerCase(), 25));
        assertFalse(cityCenterService.addBuilding(playerName, MALL.name().toLowerCase(), 1));
        assertFalse(cityCenterService.addBuilding(playerName, MALL.name().toLowerCase(), -1));
    }

    @Test
    public  void testCityCenterInfo() {
        assertNotNull(cityCenterService.cityCenterInfo(playerName));
    }

    @Test
    public void testAddBuildingOnExistingPosition() {
        int buildingPosition = 5;
        when(buildingService.getBuildingsByCityId(1L)).thenReturn(Arrays.asList(new Building(MALL, 3, buildingPosition, 1L)));

        assertFalse(cityCenterService.addBuilding(playerName, MALL.name().toLowerCase(), buildingPosition));
    }

    @Test
    public void testNoBuildingTypeIfBuildingWithAllBuildingTypesAreBuilt() {
        CityCenterDto cityCenterDtoMock = cityCenterDto(Sets.newHashSet(BuildingType.asListLowerCase()));
        Collection<String> availableForBuildBuildingTypes = cityCenterService.availableForBuildBuildingTypes(cityCenterDtoMock.getBuildingTypes());
        assertEquals(0, availableForBuildBuildingTypes.size());
    }

    @Test
    public void testAllBuildingTypesAvailableIfNoBuildingsBuilt() {
        CityCenterDto cityCenterDtoMock = cityCenterDto(new HashSet<String>());
        Collection<String> availableForBuildBuildingTypes = cityCenterService.availableForBuildBuildingTypes(cityCenterDtoMock.getBuildingTypes());
        assertEquals(BuildingType.asListLowerCase().size(), availableForBuildBuildingTypes.size());
    }

    private static CityCenterDto cityCenterDto(Collection<String> buildingTypesAsStrings) {
        return CityCenterDtoBuilder.builder().buildings(buildings(buildingTypesAsStrings)).build();
    }

    private static Map<Integer, BuildingDto> buildings(Collection<String> buildingTypesAsStrings) {
        Map<Integer, BuildingDto> map = new HashMap<>();
        Random random = new Random();
        for (String buildingTypesAsString : buildingTypesAsStrings) {
            int key = random.nextInt();

            if (!map.containsKey(key)) {
                map.put(key, new BuildingDto(buildingTypesAsString, 1, 1));
                continue;
            }

            while (map.get(key) != null) {
                key = random.nextInt();
                map.put(key, new BuildingDto(buildingTypesAsString, 1, 1));
            }
        }
        return map;
    }
}

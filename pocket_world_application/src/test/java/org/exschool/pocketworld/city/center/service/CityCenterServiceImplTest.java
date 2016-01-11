package org.exschool.pocketworld.city.center.service;

import static org.exschool.pocketworld.building.model.BuildingType.MALL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.*;

import org.exschool.pocketworld.building.BuildingDto;
import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.building.service.BuildingService;
import org.exschool.pocketworld.building.service.BuildingServiceImpl;
import org.exschool.pocketworld.city.center.builder.CityCenterDtoBuilder;
import org.exschool.pocketworld.city.center.dto.CityCenterDto;
import org.exschool.pocketworld.city.model.City;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.city.service.CityServiceImpl;
import org.exschool.pocketworld.config.TestSpringConfig;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.model.PlayerResources;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.player.service.PlayerServiceImpl;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.exschool.pocketworld.util.builder.BuildingBuilder;
import org.exschool.pocketworld.util.builder.ResourceBuildingBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.google.common.collect.Sets;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestSpringConfig.class)
public class CityCenterServiceImplTest {

	@InjectMocks
  	CityCenterService cityCenterService = new CityCenterServiceImpl();

    @Mock
	CityService cityService;
    @Mock
  	PlayerService playerService;
    @Mock
	BuildingService buildingService;

    List<Building> buildings;
    PlayerResources playerResources;

    @Before
    public void before() {
        playerResources= new PlayerResources(100, 100, 100, 100);
        Player palayer = new Player(playerResources, "login");
        buildings = new ArrayList<>();
        buildings.add(BuildingBuilder.builder().buildingType(BuildingType.BARN).level(1).position(1).cityId(1L).build());
        buildings.add(BuildingBuilder.builder().buildingType(BuildingType.FARM).level(1).position(2).cityId(1L).build());

        when(playerService.getPlayerByLogin(anyString())).thenReturn(palayer);
        when(cityService.getCityByPlayerId(anyLong())).thenReturn(new City());
        when(buildingService.getBuildingsByCityId(anyLong())).thenReturn(buildings);
    }


    @Test
    public void testAddBuilding() {
        String playerName = "player-test";
        assertTrue(cityCenterService.addBuilding(playerName,MALL.name().toLowerCase(),1,3));
        assertFalse(cityCenterService.addBuilding(playerName,MALL.name().toLowerCase(),1,1));
        assertFalse(cityCenterService.addBuilding(playerName,MALL.name().toLowerCase(),1,-1));
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

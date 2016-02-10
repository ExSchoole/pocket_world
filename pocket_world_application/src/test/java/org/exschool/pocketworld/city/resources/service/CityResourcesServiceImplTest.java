package org.exschool.pocketworld.city.resources.service;

import org.exschool.pocketworld.city.model.City;
import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.model.PlayerResources;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.resource.ResourceDto;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.building.service.ResourceBuildingService;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.exschool.pocketworld.util.builder.ResourceBuildingBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CityResourcesServiceImplTest {
    @Mock
    PlayerService playerService;
    @Mock
    CityService cityService;
    @Mock
    Player player;
    @Mock
    ResourceBuildingService resourceBuildingService;
    @InjectMocks
    CityResourcesServiceImpl cityResourcesService = new CityResourcesServiceImpl();
    List<ResourceBuilding> buildings;
    PlayerResources playerResources;
    String playerName = "login";

    @Before
    public void before() {
        playerResources = new PlayerResources(100, 100, 100, 100);
        Player player = new Player(playerResources, "login");
        buildings = new ArrayList<>();
        buildings.add(ResourceBuildingBuilder.builder().buildingType(ResourceType.GOLD).level(1).position(1).cityId(1L).build());
        buildings.add(ResourceBuildingBuilder.builder().buildingType(ResourceType.TIMBER).level(1).position(2).cityId(1L).build());

        when(playerService.getPlayerByLogin(anyString())).thenReturn(player);
        when(cityService.getCityByPlayerId(anyLong())).thenReturn(new City());
        when(resourceBuildingService.allCityResources(anyLong())).thenReturn(buildings);
    }

    @Test
    public void testCityResourcesInfo() {
        ResourceDto resourceDto = new ResourceDto(playerResources.getGoldAmount(),
                playerResources.getTimberAmount(),
                playerResources.getClayAmount(),
                playerResources.getCornAmount());

        CityResourcesDto cityResourcesDto = cityResourcesService.cityResourcesInfo(playerName);

        assertEquals(cityResourcesDto.getNickName(), "login");
        assertEquals(cityResourcesDto.getResourceDto(), resourceDto);
        assertEquals(buildings.size(), cityResourcesDto.getResourceBuildings().size());
        for (ResourceBuilding resourceBuilding : buildings) {
            assertNotNull(cityResourcesDto.getResourceBuildings().get(resourceBuilding.getPosition()));
        }
    }

    @Test
    public void testCreateResourceBuilding() {
        int notExistingPosition = 3;
        assertTrue(cityResourcesService.createResourceBuilding(playerName,"GOLD",notExistingPosition));
    }

    @Test
    public void testCreateResourceBuilding_cityContainsBuildingAtPosition() {
        int existingPosition = 2;
        assertFalse(cityResourcesService.createResourceBuilding(playerName,"GOLD",existingPosition));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateResourceBuildingNullPlayer() {
        when(playerService.getPlayerByLogin(anyString())).thenReturn(null);
        cityResourcesService.createResourceBuilding(playerName, ResourceType.GOLD.name(),3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateResourceBuildingNullCity() {
        when(cityService.getCityByPlayerId(anyLong())).thenReturn(null);
        cityResourcesService.createResourceBuilding(playerName, ResourceType.GOLD.name(),3);
    }
}



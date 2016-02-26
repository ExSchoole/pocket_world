package org.exschool.pocketworld.city.resources.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.exschool.pocketworld.buildQueue.service.BuildQueueService;
import org.exschool.pocketworld.city.model.City;
import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.dto.PositionOfBuilding;
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
    @Mock
    BuildQueueService buildQueueService;
    
    @InjectMocks
    CityResourcesServiceImpl cityResourcesService = new CityResourcesServiceImpl();
    List<ResourceBuilding> buildings;
    PlayerResources playerResources;
    
    String playerName = "player-test";

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
        when(resourceBuildingService.getAtPosition(anyLong(), anyInt())).thenReturn(buildings.get(0));
    }

    @Test
    public void testCityResourcesInfo() {
        ResourceDto resourceDto = new ResourceDto(playerResources.getGoldAmount(),
                playerResources.getTimberAmount(),
                playerResources.getClayAmount(),
                playerResources.getCornAmount());

        CityResourcesDto cityResourcesDto = cityResourcesService.cityResourcesInfo(player.getLogin());

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
        assertTrue(cityResourcesService.createResourceBuilding(new PositionOfBuilding(notExistingPosition, ResourceType.GOLD.name()), player.getLogin()));
    }

    @Test
    public void testCreateResourceBuilding_cityContainsBuildingAtPosition() {
        int existingPosition = 2;
        assertFalse(cityResourcesService.createResourceBuilding(new PositionOfBuilding(existingPosition, ResourceType.GOLD.name()), player.getLogin()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateResourceBuildingNullPlayer() {
        when(playerService.getPlayerByLogin(anyString())).thenReturn(null);
        cityResourcesService.createResourceBuilding(new PositionOfBuilding(3, ResourceType.GOLD.name()), player.getLogin());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateResourceBuildingNullCity() {
        when(cityService.getCityByPlayerId(anyLong())).thenReturn(null);
        cityResourcesService.createResourceBuilding(new PositionOfBuilding(3, ResourceType.GOLD.name()), player.getLogin());
    }
    @Test
    public void testGetInfo() {
    	List<Integer> info= new ArrayList<>();
  		Player currentPlayer = playerService.getPlayerByLogin(playerName);
  		City city = cityService.getCityByPlayerId(currentPlayer.getId());
  		ResourceBuilding building=  resourceBuildingService.getAtPosition(city.getId(), 1);
  		
  		Integer level =building.getLevel();
  		ResourceType resourceType = building.getResourceType();
  		info.add(resourceBuildingService.getTimeByBuildingTypeLevel(resourceType, level));
  		for (ResourceType r : ResourceType.values()){
		     info.add(resourceBuildingService.getResourcesByBuildingTypeLevel(resourceType, r, level));
 		}
		assertNotNull(info);
    }
    @Test
    public void testLevelUp(){
    	Player currentPlayer = playerService.getPlayerByLogin(playerName);
		City city = cityService.getCityByPlayerId(currentPlayer.getId());
		ResourceBuilding building=  resourceBuildingService.getAtPosition(city.getId(), 1);
		int level= building.getLevel();
    	building.levelUp();
    	int currentLevel = building.getLevel();
    	assertNotEquals(level, currentLevel);
    	
    }
}



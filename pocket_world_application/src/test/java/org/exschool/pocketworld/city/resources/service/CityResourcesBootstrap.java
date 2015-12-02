package org.exschool.pocketworld.city.resources.service;

import org.exschool.pocketworld.city.model.City;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.resource.builder.ResourcesBuider;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.model.Resource;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.exschool.pocketworld.util.builder.ResourceBuildingBuilder;
import org.exschool.pocketworld.util.builder.UserCityBuilder;
import org.exschool.pocketworld.player.builder.PlayerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional
public class CityResourcesBootstrap {
	@Autowired
    private Dao dao;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private CityService cityService;

    private volatile boolean bootstraped = false;

    public void fillDatabase() {
        if (bootstraped) return;
        Player player1 = PlayerBuilder.builder().login("login-1").resourceId(1L).build();
        dao.saveAll(Arrays.asList(player1));
        
        Player savedPlayer = playerService.getPlayerByLogin("login-1");
        
        Resource resources1 = ResourcesBuider.builder().resourceType(ResourceType.GOLD).playerId(savedPlayer.getId()).amount(10).build();
        Resource resources2 = ResourcesBuider.builder().resourceType(ResourceType.TIMBER).playerId(savedPlayer.getId()).amount(20).build();
        Resource resources3 = ResourcesBuider.builder().resourceType(ResourceType.CLAY).playerId(savedPlayer.getId()).amount(30).build();
        Resource resources4 = ResourcesBuider.builder().resourceType(ResourceType.CORN).playerId(savedPlayer.getId()).amount(40).build();
        dao.saveAll(Arrays.asList(resources1, resources2, resources3,resources4));
        
        City city1 = UserCityBuilder.builder().playerId(savedPlayer.getId()).name("City1").build();
        dao.saveAll(Arrays.asList(city1));
        City savedCity = cityService.getCityByPlayerId(savedPlayer.getId());
        ResourceBuilding resourceBuilding1 = ResourceBuildingBuilder.builder().buildingType(ResourceType.GOLD).level(1).position(1).cityId(savedCity.getId()).build();
        ResourceBuilding resourceBuilding2 = ResourceBuildingBuilder.builder().buildingType(ResourceType.TIMBER).level(1).position(2).cityId(savedCity.getId()).build();
        ResourceBuilding resourceBuilding3 = ResourceBuildingBuilder.builder().buildingType(ResourceType.TIMBER).level(1).position(3).cityId(savedCity.getId()).build();
       
        dao.saveAll(Arrays.asList(resourceBuilding1, resourceBuilding2, resourceBuilding3));
        bootstraped = true;
    }
}

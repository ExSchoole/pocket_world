package org.exschool.pocketworld.init;

import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.building.service.BuildingService;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.player.model.PlayerResources;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.resource.building.service.ResourceBuildingService;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Populator {
    @Autowired
    private BuildingService buildingService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ResourceBuildingService resourceBuildingService;


    @PostConstruct
    void fillDatabase() {
        String player1Login = "login-1";
        String player2Login = "login-2";
        String player3Login = "login-3";
        String password1 = "user1";
        String password2 = "user2";
        String password3 = "user3";

        Long player1Id = playerService.isPlayerExist(player1Login) ?
                playerService.getPlayerId(player1Login) :
                playerService.createPlayer(player1Login, password1, new PlayerResources(0, 1, 2, 3));

        Long player2Id = playerService.isPlayerExist(player2Login) ?
                playerService.getPlayerId(player2Login) :
                playerService.createPlayer(player2Login, password2, new PlayerResources(0, 1, 2, 3));

        Long player3Id = playerService.isPlayerExist(player3Login) ?
                playerService.getPlayerId(player3Login) :
                playerService.createPlayer(player3Login, password3, new PlayerResources(9, 9, 9, 9));

        Long city1Id = cityService.isCityExist(player1Id) ?
                cityService.getCityId(player1Id) :
                cityService.createCity(player1Id, "First 1");
        Long city2Id = cityService.isCityExist(player2Id) ?
                cityService.getCityId(player2Id) :
                cityService.createCity(player2Id, "Second  2");
        Long city3Id = cityService.isCityExist(player3Id) ?
                cityService.getCityId(player3Id) :
                cityService.createCity(player1Id, "Third  3");

        if (!buildingService.isBuildingExist(city1Id, 1)) {
            buildingService.createBuilding(city1Id, BuildingType.BARN, 1, 0);
        }
        if (!buildingService.isBuildingExist(city1Id, 2)) {
            buildingService.createBuilding(city1Id, BuildingType.FARM, 6, 1);
        }
        if (!buildingService.isBuildingExist(city1Id, 12)) {
            buildingService.createBuilding(city1Id, BuildingType.GILOTHOME, 12, 0);
        }

        if (!buildingService.isBuildingExist(city2Id, 1)) {
            buildingService.createBuilding(city2Id, BuildingType.BARN, 1, 0);
        }
        if (!buildingService.isBuildingExist(city2Id, 3)) {
            buildingService.createBuilding(city2Id, BuildingType.MARKETPLACE, 3, 1);
        }

        if (!buildingService.isBuildingExist(city3Id, 5)) {
            buildingService.createBuilding(city3Id, BuildingType.BARN, 5, 0);
        }
        if (!buildingService.isBuildingExist(city3Id, 6)) {
            buildingService.createBuilding(city3Id, BuildingType.MARKETPLACE, 6, 0);
        }

        if (!resourceBuildingService.isResBuildingExist(city1Id, 1)) {
            resourceBuildingService.createResourceBuilding(city1Id, ResourceType.CLAY, 1, 0);
        }
        if (!resourceBuildingService.isResBuildingExist(city1Id, 2)) {
            resourceBuildingService.createResourceBuilding(city1Id, ResourceType.CLAY, 2, 0);
        }
        if (!resourceBuildingService.isResBuildingExist(city1Id, 3)) {
            resourceBuildingService.createResourceBuilding(city1Id, ResourceType.CLAY, 1, 0);
        }

        if (!resourceBuildingService.isResBuildingExist(city2Id, 3)) {
            resourceBuildingService.createResourceBuilding(city2Id, ResourceType.TIMBER, 3, 0);
        }
    }
}

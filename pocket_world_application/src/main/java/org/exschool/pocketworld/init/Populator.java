package org.exschool.pocketworld.init;

import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.building.service.BuildingService;
import org.exschool.pocketworld.city.model.City;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.player.builder.PlayerBuilder;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.model.PlayerResources;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.building.service.ResourceBuildingService;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.exschool.pocketworld.util.builder.BuildingBuilder;
import org.exschool.pocketworld.util.builder.ResourceBuildingBuilder;
import org.exschool.pocketworld.util.builder.UserCityBuilder;
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

        Long player1Id = playerService.isPlayerExist(player1Login) ?
                playerService.getPlayerId(player1Login) :
                createPlayer(player1Login, new PlayerResources(10, 15, 18, 20));
        Long player2Id = playerService.isPlayerExist(player2Login) ?
                playerService.getPlayerId(player2Login) :
                createPlayer(player2Login, new PlayerResources(22, 22, 22, 22));
        Long player3Id = playerService.isPlayerExist(player3Login) ?
                playerService.getPlayerId(player3Login) :
                createPlayer(player3Login, new PlayerResources(33, 33, 33, 33));

        Long city1Id = cityService.isCityExist(player1Id) ?
                cityService.getCityId(player1Id) :
                createCity(player1Id, "First City");
        Long city2Id = cityService.isCityExist(player2Id) ?
                cityService.getCityId(player2Id) :
                createCity(player2Id, "Second City");
        Long city3Id = cityService.isCityExist(player3Id) ?
                cityService.getCityId(player3Id) :
                createCity(player3Id, "Third City");

        if (!buildingService.isBuildingExist(city1Id, 1)) {
            createBuilding(city1Id, BuildingType.BARN, 1, 0);
        }
        if (!buildingService.isBuildingExist(city1Id, 2)) {
            createBuilding(city1Id, BuildingType.FARM, 2, 1);
        }
        if (!buildingService.isBuildingExist(city1Id, 12)) {
            createBuilding(city1Id, BuildingType.GILOTHOME, 12, 0);
        }

        if (!buildingService.isBuildingExist(city2Id, 1)) {
            createBuilding(city2Id, BuildingType.BARN, 1, 0);
        }
        if (!buildingService.isBuildingExist(city2Id, 3)) {
            createBuilding(city2Id, BuildingType.MARKETPLACE, 3, 1);
        }

        if (!buildingService.isBuildingExist(city3Id, 5)) {
            createBuilding(city3Id, BuildingType.BARN, 5, 0);
        }
        if (!buildingService.isBuildingExist(city3Id, 6)) {
            createBuilding(city3Id, BuildingType.MARKETPLACE, 6, 0);
        }

        if (!resourceBuildingService.isResBuildingExist(city1Id, 1)) {
            createResourceBuilding(city1Id, ResourceType.CLAY, 1, 0);
        }
        if (!resourceBuildingService.isResBuildingExist(city1Id, 2)) {
            createResourceBuilding(city1Id, ResourceType.CLAY, 2, 0);
        }
        if (!resourceBuildingService.isResBuildingExist(city1Id, 3)) {
            createResourceBuilding(city1Id, ResourceType.CORN, 3, 1);
        }

        if (!resourceBuildingService.isResBuildingExist(city2Id, 3)) {
            createResourceBuilding(city2Id, ResourceType.TIMBER, 3, 0);
        }
    }

    private boolean isResBuildingExist(Long cityId, int position) {
        return resourceBuildingService.getAtPosition(cityId, position) != null;
    }

    private Long createResourceBuilding(Long cityId, ResourceType resourceType, int position, int level) {
        ResourceBuilding resourceBuilding = ResourceBuildingBuilder.builder()
                .buildingType(resourceType)
                .cityId(cityId)
                .level(level)
                .position(position)
                .build();

        resourceBuildingService.save(resourceBuilding);
        return resourceBuilding.getId();
    }

    private Long createBuilding(Long cityId, BuildingType buildingType, int position, int level) {
        Building building = BuildingBuilder.builder()
                .buildingType(buildingType)
                .cityId(cityId)
                .level(level)
                .position(position)
                .build();
        buildingService.save(building);
        return building.getId();
    }

    private Long createCity(Long playerId, String cityName) {
        City city = UserCityBuilder.builder()
                .name(cityName)
                .playerId(playerId)
                .build();
        cityService.save(city);
        return city.getId();
    }

    private Long createPlayer(String playerLogin, PlayerResources playerResources) {
        Player player = PlayerBuilder.builder()
                .login(playerLogin)
                .playerResources(playerResources)
                .build();
        playerService.savePlayer(player);
        return player.getId();
    }
}

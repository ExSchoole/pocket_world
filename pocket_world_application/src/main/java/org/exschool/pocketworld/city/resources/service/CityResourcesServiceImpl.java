package org.exschool.pocketworld.city.resources.service;

import org.exschool.pocketworld.city.model.City;
import org.exschool.pocketworld.city.resources.builder.CityResourcesDtoBuilder;
import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.model.PlayerResources;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.resource.ResourceDto;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.building.service.ResourceBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("CityResources")
public class CityResourcesServiceImpl implements CityResourcesService {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private ResourceBuildingService resourceBuildingService;
    @Autowired
    private CityService cityService;

    @Override
    public CityResourcesDto cityResourcesInfo() {
        String login = "login-1";
        Player player = playerService.getPlayerByLogin(login);
        PlayerResources playerResources = player.getPlayerResources();

        ResourceDto resourceDto =
                new ResourceDto(playerResources.getGoldAmount(),
                        playerResources.getTimberAmount(),
                        playerResources.getClayAmount(),
                        playerResources.getCornAmount());

        City city = cityService.getCityByPlayerId(player.getId());
        List<ResourceBuilding> buildings = resourceBuildingService.allCityBuildings(city.getId());

        Map<Integer, ResourceBuilding> resourceBuildings = toResourceBuildingsDTOs(buildings);

        return CityResourcesDtoBuilder.builder()
                .resource(resourceDto)
                .resourceBuildings(resourceBuildings)
                .nickname(login)
                .build();
    }

    private static Map<Integer, ResourceBuilding> toResourceBuildingsDTOs(List<ResourceBuilding> resourceBuildings) {
        Map<Integer, ResourceBuilding> result = new HashMap<>();
        for (ResourceBuilding resourceBuilding : resourceBuildings) {
            result.put(resourceBuilding.getPosition(), resourceBuilding);
        }
        return result;
    }
}
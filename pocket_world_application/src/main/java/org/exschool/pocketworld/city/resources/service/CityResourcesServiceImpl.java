package org.exschool.pocketworld.city.resources.service;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.exschool.pocketworld.building.ResourceBuildingDto;
import org.exschool.pocketworld.city.model.City;
import org.exschool.pocketworld.city.resources.builder.CityResourcesDtoBuilder;
import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.dto.PositionOfBuilding;
import org.exschool.pocketworld.player.builder.PlayerBuilder;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.model.PlayerResources;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.resource.ResourceDto;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.building.service.ResourceBuildingService;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.exschool.pocketworld.util.builder.ResourceBuildingBuilder;
import org.exschool.pocketworld.util.builder.UserCityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.apache.commons.lang.Validate.notNull;

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
        notNull(player);
        PlayerResources playerResources = player.getPlayerResources();

        ResourceDto resourceDto = new ResourceDto(playerResources.getGoldAmount(),
                playerResources.getTimberAmount(),
                playerResources.getClayAmount(),
                playerResources.getCornAmount());

        City city = cityService.getCityByPlayerId(player.getId());
        notNull(city);

        List<ResourceBuilding> buildings = resourceBuildingService.allCityResources(city.getId());
        notNull(buildings);

        List<ResourceBuildingDto> resourceBuildingDtos = Lists.transform(buildings, TO_RESOURCE_BUILDING_DTO);

        return CityResourcesDtoBuilder.builder()
                .resource(resourceDto)
                .resourceBuildings(resourceBuildingDtos)
                .nickname(player.getLogin())
                .build();
    }

    @Override
    public void createResourceBuilding(final PositionOfBuilding positionOfBuilding) {
        final Integer INITIAL_BUILDING_LEVEL = 0;

        String playerLogin = "login-1";
        Player player = playerService.getPlayerByLogin(playerLogin);
        notNull(player);
        City city = cityService.getCityByPlayerId(player.getId());
        notNull(city);
        //check position is free
        List<ResourceBuilding> resourceBuildings = resourceBuildingService.allCityResources(city.getId());
        Optional<ResourceBuilding> resourceBuildingAtPosition = Iterables.tryFind(resourceBuildings,
                new Predicate<ResourceBuilding>() {
                    @Override
                    public boolean apply(ResourceBuilding resourceBuilding) {
                        return resourceBuilding.getPosition() == positionOfBuilding.getPosition();
                    }
                });

        if (resourceBuildingAtPosition.isPresent()) throw new RuntimeException();

        ResourceType resourceType = ResourceType.valueOf(positionOfBuilding.getType().toUpperCase());

        ResourceBuilding resourceBuilding = ResourceBuildingBuilder.builder()
                .buildingType(resourceType)
                .cityId(city.getId())
                .level(INITIAL_BUILDING_LEVEL)
                .position(positionOfBuilding.getPosition())
                .build();

        resourceBuildingService.save(resourceBuilding);
    }

    @PostConstruct
    public void afterInitialization() {
        // -- temporary
        String playerLogin = "login-1";
        if (playerService.getPlayerByLogin(playerLogin) == null) {
            Player player = PlayerBuilder.builder()
                    .login("login-1")
                    .playerResources(new PlayerResources(1, 1, 1, 1))
                    .build();
            playerService.savePlayer(player);

            if (cityService.getCityByPlayerId(player.getId()) == null) {
                City city = UserCityBuilder.builder()
                        .name("First City")
                        .playerId(player.getId())
                        .build();

                cityService.save(city);
            }
        }
        // -- end temporary
    }

    private static final Function<ResourceBuilding, ResourceBuildingDto> TO_RESOURCE_BUILDING_DTO =
            new Function<ResourceBuilding, ResourceBuildingDto>() {
                @Override
                public ResourceBuildingDto apply(ResourceBuilding resourceBuilding) {
                    notNull(resourceBuilding);
                    return ResourceBuildingDto.builder().
                            type(resourceBuilding.getResourceType().name()).
                            level(resourceBuilding.getLevel()).
                            position(resourceBuilding.getPosition()).
                            build();

                }
            };
}
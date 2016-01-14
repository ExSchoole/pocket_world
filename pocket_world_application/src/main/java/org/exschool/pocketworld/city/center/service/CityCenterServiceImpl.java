package org.exschool.pocketworld.city.center.service;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.buildQueue.model.Status;
import org.exschool.pocketworld.buildQueue.model.Type;
import org.exschool.pocketworld.buildQueue.service.BuildQueueService;
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
import org.exschool.pocketworld.resource.ResourceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.base.Optional;

import java.util.*;

import static org.apache.commons.lang.Validate.notNull;
import static org.exschool.pocketworld.building.model.BuildingType.*;

@Service
public class CityCenterServiceImpl implements CityCenterService {

    @Autowired
    private BuildingService buildingService;
    @Autowired
    private CityService cityService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private BuildQueueService buildQueueService;



    public static final int MIN_POSITION = 1;
    public static final int MAX_POSITION = 12;

    private void initialization(String playerName) {
        String cityName = "City name";
        PlayerResources playerResources = new PlayerResources(1, 1, 1, 1);
        Player player = new Player(playerResources, playerName);
        playerService.savePlayer(player);

        City city = new City(player.getId(), cityName);
        cityService.save(city);

        buildingService.save(new Building(MALL, 1, 1, city.getId()));
        buildingService.save(new Building(PLANT, 1, 3, city.getId()));
        buildingService.save(new Building(MARKETPLACE, 1, 6, city.getId()));
        buildingService.save(new Building(POOL, 1, 9, city.getId()));
    }

    @Override
    public CityCenterDto cityCenterInfo(String playerName) {
        initialization(playerName);

        Player player = playerService.getPlayerByLogin(playerName);
        notNull(player);
        City city = cityService.getCityByPlayerId(player.getId());
        notNull(city);
        PlayerResources playerResources = player.getPlayerResources();
        notNull(playerResources);

        return CityCenterDtoBuilder.builder()
                .resource(new ResourceDto(playerResources))
                .buildings(buildingDtosByPosition(buildingService.getBuildingsByCityId(city.getId())))
                .nickname(playerName)
                .build();
    }

    /**
     * Gets list of available building types (Buildings of those types has not been built yet)
     *
     * @param buildingTypesOfBuiltBuildings - BuildingTypes of building which are already built in the city
     * @return list of building types which we allowed to build
     */

    @Override
    public Collection<String> availableForBuildBuildingTypes(final Set<String> buildingTypesOfBuiltBuildings) {
        return Collections2.filter(BuildingType.asListLowerCase(), new Predicate<String>() {
            @Override
            public boolean apply(String buildingType) {
                return !buildingTypesOfBuiltBuildings.contains(buildingType);
            }
        });
    }

    @Override
    public boolean addBuilding(String playerName, String type, final int position) {
        if (position > MAX_POSITION || position < MIN_POSITION) return false;
        Long userId = playerService.getPlayerByLogin(playerName).getId();
        City city = cityService.getCityByPlayerId(userId);
        notNull(city);
        Long cityId = city.getId();
        List<Building> buildings = buildingService.getBuildingsByCityId(cityId);

        Optional<Building> buildingAtPosition = Iterables.tryFind(buildings, new Predicate<Building>() {
            @Override
            public boolean apply(Building building) {
                return building.getPosition() == position;
            }
        });

        if (buildingAtPosition.isPresent()) {
            return false;
        }

        Building buildingEntity = new Building();
        buildingEntity.setCityId(cityId);
        buildingEntity.setLevel(1);
        buildingEntity.setPosition(position);
        buildingEntity.setBuildingType(BuildingType.valueOf(type.toUpperCase()));
        Building savedBuilding =buildingService.save(buildingEntity);

        BuildQueueRecord newRecord = new BuildQueueRecord();
        newRecord.setName(playerName);
        newRecord.setLevel(savedBuilding.getLevel());
        newRecord.setType(Type.BUILDING);
        newRecord.setBuildEnd(new Date(System.currentTimeMillis()));
        newRecord.setUserId(userId);
        newRecord.setStatus(Status.DONE);
        newRecord.setBuildingId(savedBuilding.getId());
        buildQueueService.save(newRecord);
        return true;
    }

    private static Map<Integer, BuildingDto> buildingDtosByPosition(List<Building> buildingsFromDataBase) {
        Map<Integer, BuildingDto> buildingsDto = new HashMap<>();
        for (Building b : buildingsFromDataBase) {
            buildingsDto.put(b.getPosition(), new BuildingDto(b));
        }

        return buildingsDto;
    }

    public void setBuildingService(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }
}

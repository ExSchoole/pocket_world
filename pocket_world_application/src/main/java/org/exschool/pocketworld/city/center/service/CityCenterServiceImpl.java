package org.exschool.pocketworld.city.center.service;

import static org.apache.commons.lang.Validate.notNull;
import static org.exschool.pocketworld.building.model.BuildingType.MALL;
import static org.exschool.pocketworld.building.model.BuildingType.MARKETPLACE;
import static org.exschool.pocketworld.building.model.BuildingType.PLANT;
import static org.exschool.pocketworld.building.model.BuildingType.POOL;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.buildQueue.model.Status;
import org.exschool.pocketworld.buildQueue.model.Type;
import org.exschool.pocketworld.buildQueue.service.BuildQueueService;
import org.exschool.pocketworld.building.BuildingDto;
import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.building.model.BuildingResourceId;
import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.building.model.TimeId;
import org.exschool.pocketworld.building.service.BuildingService;
import org.exschool.pocketworld.city.center.builder.CityCenterDtoBuilder;
import org.exschool.pocketworld.city.center.dto.CityCenterDto;
import org.exschool.pocketworld.city.model.City;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.model.PlayerResources;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.resource.ResourceDto;
import org.exschool.pocketworld.util.builder.BuildQueueBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;


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

    @PostConstruct
    private void fillDataBaseInfo(){
    	buildingService.saveAllInformation();
    }
    
    public static final int MIN_POSITION = 1;
    public static final int MAX_POSITION = 12;
    private static final int INITIAL_BUILDING_LEVEL=0;


    
    private void initialization(String playerName) { //temporary
        if(playerService.getPlayerByLogin(playerName)==null) {
            String cityName = "City name";
            PlayerResources playerResources = new PlayerResources(1, 1, 1, 1);
            Player player = new Player(playerResources, playerName);
            playerService.savePlayer(player);

            City city = new City(player.getId(), cityName);
            cityService.save(city);

            buildingService.save(new Building(MALL, 1, 1, city.getId()));
            buildingService.save(new Building(PLANT, 1, 3, city.getId()));
            buildingService.save(new Building(MARKETPLACE, 1, 6, city.getId()));
            
            Building testBuilding = new Building(POOL, INITIAL_BUILDING_LEVEL, 9, city.getId()); 
            buildingService.save(testBuilding);
            
            Long buildingTimeMillis = (long) buildingService.getTimeByBuildingTypeLevel(
            		testBuilding.getBuildingType(),
                    1) *1000;
            
            BuildQueueRecord record = BuildQueueBuilder.builder()
            		.position(9)
            		.name(testBuilding.getBuildingType().name().toLowerCase())
                    .level(1)
                    .type(Type.BUILDING)
                    .buildEnd(new DateTime(System.currentTimeMillis() + buildingTimeMillis )
                    					.withZone(DateTimeZone.UTC).toDate())
                    .userId(player.getId())
                    .status(Status.QUEUED)
                    .buildingId(testBuilding.getId()).build();
                    
            System.out.println("TIME: ");
            
            buildQueueService.save(record);
        }
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
                .resourceInfo(getResourceInfo(buildingService.getResourceBuildingInfo()))
                .timeInfo(getTimeInfo(buildingService.getTimeInfo()))
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
        buildingEntity.setLevel(INITIAL_BUILDING_LEVEL);
        buildingEntity.setPosition(position);
        buildingEntity.setBuildingType(BuildingType.valueOf(type.toUpperCase()));

        Building savedBuilding = buildingService.save(buildingEntity);
        int nextLevel = savedBuilding.getLevel()+1;
        Long buildingTimeMillis = (long) buildingService.getTimeByBuildingTypeLevel(
                                                savedBuilding.getBuildingType(),
                                                nextLevel) *1000;
        BuildQueueRecord record = BuildQueueBuilder.builder().name(type)
        		.position(position)
                .level(nextLevel)
                .type(Type.BUILDING)
                .buildEnd(new DateTime(System.currentTimeMillis() + buildingTimeMillis )
                					.withZone(DateTimeZone.UTC).toDate())
                .userId(userId)
                .status(Status.QUEUED)
                .buildingId(savedBuilding.getId()).build();
        buildQueueService.save(record);
        return true;
    }

    public int getTimeInfo(String type, int level){
    	return buildingService.getTimeByBuildingTypeLevel(BuildingType.valueOf(type), level);
    }
    
    private static Map<Integer, BuildingDto> buildingDtosByPosition(List<Building> buildingsFromDataBase) {
        Map<Integer, BuildingDto> buildingsDto = new HashMap<>();
        for (Building b : buildingsFromDataBase) {
            buildingsDto.put(b.getPosition(), new BuildingDto(b));
        }

        return buildingsDto;
    }
    
    private static Map<BuildingResourceId, Integer> getResourceInfo(Map<BuildingResourceId,Integer> resourceInfoFromDB){
    	 Map<BuildingResourceId, Integer> resourceInfo = new HashMap<>();
    	 for (Entry<BuildingResourceId, Integer> b : resourceInfoFromDB.entrySet())
    		 resourceInfo.put(b.getKey(), b.getValue());
    		 
    	 return resourceInfo;
    }
    
    private static Map<TimeId, Integer> getTimeInfo(Map<TimeId, Integer> timeInfoFromDB){
   	 Map<TimeId, Integer> timeInfo = new HashMap<>();
   	 for (Entry<TimeId, Integer> b : timeInfoFromDB.entrySet())
   		 timeInfo.put(b.getKey(), b.getValue());
   		 
   	 return timeInfo;
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

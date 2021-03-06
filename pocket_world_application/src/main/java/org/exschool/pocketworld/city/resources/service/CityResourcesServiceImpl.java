package org.exschool.pocketworld.city.resources.service;

import static org.apache.commons.lang.Validate.notNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.exschool.pocketworld.buildQueue.model.BuildQueueRecord;
import org.exschool.pocketworld.buildQueue.model.Status;
import org.exschool.pocketworld.buildQueue.model.Type;
import org.exschool.pocketworld.buildQueue.service.BuildQueueService;
import org.exschool.pocketworld.building.ResourceBuildingDto;
import org.exschool.pocketworld.city.model.City;
import org.exschool.pocketworld.city.resources.builder.CityResourcesDtoBuilder;
import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.dto.BuildingInfo;
import org.exschool.pocketworld.dto.PositionOfBuilding;
import org.exschool.pocketworld.player.builder.PlayerBuilder;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.model.PlayerResources;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.resource.ResourceDto;
import org.exschool.pocketworld.resource.building.model.BuildingResourceId;
import org.exschool.pocketworld.resource.building.model.ProductionId;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.building.model.TimeId;
import org.exschool.pocketworld.resource.building.service.ResourceBuildingService;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.exschool.pocketworld.util.builder.BuildQueueBuilder;
import org.exschool.pocketworld.util.builder.ResourceBuildingBuilder;
import org.exschool.pocketworld.util.builder.UserCityBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Component("CityResources")
public class CityResourcesServiceImpl implements CityResourcesService {
    private static final Integer INITIAL_BUILDING_LEVEL = 0;

    @Autowired
    private PlayerService playerService;
    @Autowired
    private ResourceBuildingService resourceBuildingService;
    @Autowired
    private CityService cityService;
    @Autowired
    private BuildQueueService buildQueueService;

    @PostConstruct
    private void fillDataBaseInfo() {
        resourceBuildingService.saveAllInformation();
    }
    
    @Override
    public CityResourcesDto cityResourcesInfo(String playerName) {    	
        Player player = playerService.getPlayerByLogin(playerName);
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
                .productionInfo(getProductionInfo(resourceBuildingService.getPRODUCTION_RESOURCE_BUILDINGS_INFO()))
                .resourceInfo(getResourceInfo(resourceBuildingService.getRESOURCE_BUILDINGS_INFO()))
                .timeInfo(getTimeInfo(resourceBuildingService.getTIME_RESOURCE_BUILDINGS_INFO()))
                .cityName(city.getName())
                .build();
    }

    @Override
    public boolean createResourceBuilding(final PositionOfBuilding positionOfBuilding, String playerName) {
        Player player = playerService.getPlayerByLogin(playerName);
        notNull(player);
        City city = cityService.getCityByPlayerId(player.getId());
        notNull(city);
        
        Long userId = player.getId();
        //check position is free
        List<ResourceBuilding> resourceBuildings = resourceBuildingService.allCityResources(city.getId());
        Optional<ResourceBuilding> resourceBuildingAtPosition = Iterables.tryFind(resourceBuildings,
                new Predicate<ResourceBuilding>() {
                    @Override
                    public boolean apply(ResourceBuilding resourceBuilding) {
                        return resourceBuilding.getPosition() == positionOfBuilding.getPosition();
                    }
                });

        if (resourceBuildingAtPosition.isPresent()) return false;

        reduceResources(player, ResourceType.valueOf(positionOfBuilding.getType().toUpperCase()), 1);
        
        ResourceType resourceType = ResourceType.valueOf(positionOfBuilding.getType().toUpperCase());

        ResourceBuilding resourceBuilding = ResourceBuildingBuilder.builder()
                .buildingType(resourceType)
                .cityId(city.getId())
                .level(INITIAL_BUILDING_LEVEL)
                .position(positionOfBuilding.getPosition())
                .build();

        resourceBuildingService.save(resourceBuilding);
        
        int nextLevel = resourceBuilding.getLevel()+1;
        Long buildingTimeMillis = (long) resourceBuildingService.getTimeByBuildingTypeLevel(
        								 resourceBuilding.getResourceType(),
                                         nextLevel) *1000;
        
        BuildQueueRecord record = BuildQueueBuilder.builder().name(positionOfBuilding.getType())
        		.position(positionOfBuilding.getPosition())
                .level(nextLevel)
                .type(Type.RESOURCE_BUILDING)
                .buildEnd(new DateTime(System.currentTimeMillis() + buildingTimeMillis )
                					.withZone(DateTimeZone.UTC).toDate())
                .userId(userId)
                .status(Status.QUEUED)
                .buildingId(resourceBuilding.getId()).build();
        buildQueueService.save(record);
        
        return true;
    }

    @Override
    public boolean checkResources(String playerName, String resourceBuildingType, int level){
    	PlayerResources playerResources = playerService.getPlayerByLogin(playerName).getPlayerResources();
    	for (ResourceType resourceType : ResourceType.values())
    		if (resourceBuildingService.getResourcesByBuildingTypeLevel(
    				ResourceType.valueOf(resourceBuildingType.toUpperCase()), resourceType, level)
    				>playerResources.getAmount(resourceType)) return false;
    	
    	return true;
    }
    
    @Override
    public void reduceResources(Player player, ResourceType resourceBuildingType, int level){
    	for (ResourceType resourceType : ResourceType.values())
    		player.getPlayerResources().setAmount(resourceType, 
    				player.getPlayerResources().getAmount(resourceType) - 
    				resourceBuildingService.getResourcesByBuildingTypeLevel(
    						resourceBuildingType, resourceType, level));
    	
    	playerService.savePlayer(player);
    }
    
//    @PostConstruct
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
    

    public int getTimeInfo(String type, int level){
    	return resourceBuildingService.getTimeByBuildingTypeLevel(ResourceType.valueOf(type), level);
    }


    public void levelUp(String playerName, int position) {
    	Player player = playerService.getPlayerByLogin(playerName);        
        Long userId = player.getId();
        City city = cityService.getCityByPlayerId(userId);
        notNull(city);
        Long cityId = city.getId();
    	
    	ResourceBuilding building = resourceBuildingService.getAtPosition(cityId, position);
    	
    	Long buildingTimeMillis = (long) resourceBuildingService.getTimeByBuildingTypeLevel(
                building.getResourceType(),
                building.getLevel()+1)*1000;
    	
    	reduceResources(player, building.getResourceType(), building.getLevel()+1);
    	
    	BuildQueueRecord record = BuildQueueBuilder.builder().name(building.getResourceType().name().toLowerCase())
        		.position(position)
                .level(building.getLevel()+1)
                .type(Type.RESOURCE_BUILDING)
                .buildEnd(new DateTime(System.currentTimeMillis() + buildingTimeMillis )
                					.withZone(DateTimeZone.UTC).toDate())
                .userId(userId)
                .status(Status.QUEUED)
                .buildingId(building.getId()).build();
    	
        buildQueueService.save(record); 		
  	}

  	@Override
  	public BuildingInfo getInfo(String playerName, int position) {
  		BuildingInfo buildingInfo = new BuildingInfo();
  		
  		Player currentPlayer = playerService.getPlayerByLogin(playerName);
  		City city = cityService.getCityByPlayerId(currentPlayer.getId());
  		ResourceBuilding building = resourceBuildingService.getAtPosition(city.getId(), position);
  		
  		if (building.getLevel()==3){ 
  			return null; 
  		}
  		
  		buildingInfo.setLevel(building.getLevel());
  		buildingInfo.setType(building.getResourceType().name().toLowerCase());
  		buildingInfo.setTime(resourceBuildingService.getTimeByBuildingTypeLevel(
  				building.getResourceType(), building.getLevel()+1));
  		buildingInfo.setResourceDto(new ResourceDto());
  		
  		for (ResourceType r : ResourceType.values()){
  			buildingInfo.getResourceDto().setAmount(r, 
  					resourceBuildingService.getResourcesByBuildingTypeLevel(
  							building.getResourceType(), r, building.getLevel()+1)); 
  		}
  		
  		return buildingInfo;
  	}

    private static Map<BuildingResourceId, Integer> getResourceInfo(Map<BuildingResourceId, Integer> resourcesInfo) {
        Map<BuildingResourceId, Integer> result = new HashMap<>();
        for (Entry<BuildingResourceId, Integer> b : resourcesInfo.entrySet())
            result.put(b.getKey(), b.getValue());

        return result;
    }

    private static Map<TimeId, Integer> getTimeInfo(Map<TimeId, Integer> timeInfoFromDB) {
        Map<TimeId, Integer> timeInfo = new HashMap<>();
        for (Entry<TimeId, Integer> b : timeInfoFromDB.entrySet())
            timeInfo.put(b.getKey(), b.getValue());

        return timeInfo;
    }

    private static Map<ProductionId, Integer> getProductionInfo(Map<ProductionId, Integer> productionInfoFromDB) {
        Map<ProductionId, Integer> productionInfo = new HashMap<>();
        for (Entry<ProductionId, Integer> b : productionInfoFromDB.entrySet())
            productionInfo.put(b.getKey(), b.getValue());

        return productionInfo;
    }

    private static final Function<ResourceBuilding, ResourceBuildingDto> TO_RESOURCE_BUILDING_DTO =
            new Function<ResourceBuilding, ResourceBuildingDto>() {
                @Override
                public ResourceBuildingDto apply(ResourceBuilding resourceBuilding) {
                    notNull(resourceBuilding);
                    return ResourceBuildingDto.builder().
                            type(resourceBuilding.getResourceType().name().toLowerCase()).
                            level(resourceBuilding.getLevel()).
                            position(resourceBuilding.getPosition()).
                            build();

                }
            };
    
    
}
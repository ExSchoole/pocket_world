package org.exschool.pocketworld.city.resources.service;

import static org.apache.commons.lang.Validate.notNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.exschool.pocketworld.building.ResourceBuildingDto;
import org.exschool.pocketworld.city.model.City;
import org.exschool.pocketworld.city.resources.builder.CityResourcesDtoBuilder;
import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.model.PlayerResources;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.resource.ResourceDto;
import org.exschool.pocketworld.resource.building.model.BuildingResourceId;
import org.exschool.pocketworld.resource.building.model.ProductionId;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.building.model.TimeId;
import org.exschool.pocketworld.resource.building.service.ResourceBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Component("CityResources")
public class CityResourcesServiceImpl implements CityResourcesService {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private ResourceBuildingService resourceBuildingService;
    @Autowired
    private CityService cityService;
    
    @PostConstruct
    private void fillDataBaseInfo(){
    	resourceBuildingService.saveAllInformation();
    }
    
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

        List<ResourceBuilding> buildings = resourceBuildingService.allCityBuildings(city.getId());
        notNull(buildings);

        List<ResourceBuildingDto> resourceBuildingDtos = Lists.transform(buildings, TO_RESOURCE_BUILDING_DTO);

        return CityResourcesDtoBuilder.builder()
                .resource(resourceDto)
                .resourceBuildings(resourceBuildingDtos)
                .nickname(player.getLogin())
                .productionInfo(getProductionInfo(resourceBuildingService.getPRODUCTION_RESOURCE_BUILDINGS_INFO()))
                .resourceInfo(getResourceInfo(resourceBuildingService.getRESOURCE_BUILDINGS_INFO()))
                .timeInfo(getTimeInfo(resourceBuildingService.getTIME_RESOURCE_BUILDINGS_INFO()))
                .build();
    }
    
   private static Map<BuildingResourceId, Integer> getResourceInfo(Map<BuildingResourceId, Integer> resourceInfoFromDB){
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
   
   private static Map<ProductionId, Integer> getProductionInfo(Map<ProductionId, Integer> productionInfoFromDB){
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
                            type(resourceBuilding.getResourceType().name()).
                            level(resourceBuilding.getLevel()).
                            position(resourceBuilding.getPosition()).
                            build();

                }
            };
}
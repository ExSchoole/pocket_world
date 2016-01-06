package org.exschool.pocketworld.city.center.service;

import static org.exschool.pocketworld.building.model.BuildingType.MALL;
import static org.exschool.pocketworld.building.model.BuildingType.MARKETPLACE;
import static org.exschool.pocketworld.building.model.BuildingType.PLANT;
import static org.exschool.pocketworld.building.model.BuildingType.POOL;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

@Service
public class CityCenterServiceImpl implements CityCenterService {
	
    @Autowired 
    private BuildingService buildingService;
    @Autowired
    private CityService cityService;
    @Autowired
    private PlayerService playerService;
	
	public static final int MIN_POSITION = 1;
    public static final int MAX_POSITION = 12;
    
    String nickName;
    Map<Integer,BuildingDto> buildings;


   private void initialization(){
	   buildings = new HashMap<>();
	   
	   nickName = "User-login";
       String cityName = "City name";
       PlayerResources playerResources = new PlayerResources(1,1,1,1);
       Player player = new Player(playerResources,nickName);
       playerService.savePlayer(player);
       
       City city = new City(player.getId(),cityName);
       cityService.save(city);
       
       buildingService.save(new Building(MALL,1,1,city.getId()));
       buildingService.save(new Building(PLANT,1,3,city.getId()));
       buildingService.save(new Building(MARKETPLACE,1,6,city.getId()));
       buildingService.save(new Building(POOL,1,9,city.getId()));
   }
    
    @Override
    public CityCenterDto cityCenterInfo() {
    	initialization();
    	
    	Player player = playerService.getPlayerByLogin(nickName);
    	PlayerResources playerResources = player.getPlayerResources();
    
    	ResourceDto resourcesDto = new ResourceDto(playerResources);
    	
    	List<Building> buildingsFromDataBase =  buildingService.allBuildings();
    	
    	for (Building b : buildingsFromDataBase){
    		buildings.put(b.getPosition(), new BuildingDto(b));
    	}
    	
        return CityCenterDtoBuilder.builder()
                .resource(resourcesDto)
                .buildings(buildings)
                .nickname(nickName)
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


    
    public boolean addBuilding(BuildingDto newBuilding) {
        if (newBuilding != null && newBuilding.getPosition() <= MAX_POSITION && newBuilding.getPosition() >= MIN_POSITION) {
            if (!buildings.containsKey(newBuilding.getPosition())) {
                this.buildings.put(newBuilding.getPosition(), newBuilding);
                
                Building buildingEntity = new Building();
                buildingEntity.setCityId(cityService.getCityByPlayerId(playerService.getPlayerByLogin(nickName).getId()).getId());
                buildingEntity.setLevel(newBuilding.getLevel());
                buildingEntity.setPosition(newBuilding.getPosition());                
                buildingEntity.setBuildingType(BuildingType.valueOf(newBuilding.getType().toUpperCase()));
                              
                buildingService.save(buildingEntity);
                return true;
                
            }
        }

        return false;
    }
    
    public void setBuildingService(BuildingService buildingService) {
        this.buildingService = buildingService;
    }
    
}

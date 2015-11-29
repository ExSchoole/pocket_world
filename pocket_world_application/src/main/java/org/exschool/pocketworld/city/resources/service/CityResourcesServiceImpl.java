package org.exschool.pocketworld.city.resources.service;

<<<<<<< HEAD

import org.exschool.pocketworld.city.model.City;
import org.exschool.pocketworld.city.resources.builder.CityResourcesDtoBuilder;
import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.resource.ResourceDto;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.building.service.ResourceBuildingService;
import org.exschool.pocketworld.resource.model.Resources;
import org.exschool.pocketworld.resource.service.ResourceService;
import org.springframework.stereotype.Service;



import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
=======
import org.exschool.pocketworld.building.ResourceBuilding;
import org.exschool.pocketworld.city.resources.builder.CityResourcesDtoBuilder;
import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.resource.ResourceDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
>>>>>>> origin/master
import java.util.Map;

@Service
public class CityResourcesServiceImpl implements CityResourcesService {
<<<<<<< HEAD
	
	private PlayerService	playerService;
	private ResourceBuildingService resourceBuildingService;
	private ResourceService resourceService;
	private CityService cityService;
	 
    @Override
    public CityResourcesDto cityResourcesInfo() {
    	String login="login";
    	Player player= playerService.getPlayerByLogin(login);
    	
    	Resources resources= resourceService.getResourcesById(player.getResourcesID());
    	
        ResourceDto resourceDto = 
        		new ResourceDto(resources.getGold().getAmount(),
        						resources.getTimber().getAmount(),
        						resources.getClay().getAmount(),
        						resources.getCorn().getAmount());
        
        City city = cityService.getCityByPlayerId(player.getId());
       
        Map<Integer, ResourceBuilding> resourceBuildings = resourceBuildings(city.getId());
  
        return CityResourcesDtoBuilder.builder()
                .resource(resourceDto)
                .resourceBuildings(resourceBuildings)
                .nickname(login)
                .build();
    }
    
    Map<Integer, ResourceBuilding> resourceBuildings(Long cityId){
    	List<ResourceBuilding> buildings = resourceBuildingService.allCityBuildings(cityId);
        
        Map<Integer, ResourceBuilding> resourceBuildings = new  HashMap<>();
        Iterator<ResourceBuilding> itr=buildings.iterator();
        while(itr.hasNext()){
        	ResourceBuilding resourceBuilding= itr.next();
        	resourceBuildings.put(resourceBuilding.getPosition(),resourceBuilding);
        }
        return resourceBuildings;
    }

    
=======

    @Override
    public CityResourcesDto cityResourcesInfo() {
        ResourceDto resourceDto = new ResourceDto(1,1,1,1);
        Map<Integer, ResourceBuilding> resourceBuildings = resourceBuildings();
        String nickname = "User login";
        return CityResourcesDtoBuilder.builder()
                .resource(resourceDto)
                .resourceBuildings(resourceBuildings)
                .nickname(nickname)
                .build();
    }

    private Map<Integer, ResourceBuilding> resourceBuildings() {
        Map<Integer, ResourceBuilding> resourceBuildings = new HashMap<>();
        resourceBuildings.put(2, new ResourceBuilding("clay", 1));
        resourceBuildings.put(4, new ResourceBuilding("gold", 2));
        resourceBuildings.put(8, new ResourceBuilding("timber", 3));
        resourceBuildings.put(12, new ResourceBuilding("corn", 4));
        return resourceBuildings;
    }
>>>>>>> origin/master
}
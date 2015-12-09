package org.exschool.pocketworld.city.resources.service;


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
import java.util.Map;

@Service
public class CityResourcesServiceImpl implements CityResourcesService {
	
	PlayerService	playerService;
	ResourceBuildingService resourceBuildingService;
	ResourceService resourceService;
	CityService cityService;
	 
    @Override
    public CityResourcesDto cityResourcesInfo() {
    	String login="login";
    	Player player= playerService.getPlayerByLogin(login);
    	
    	Resources resources= resourceService.getResourcesById(player.getResourcesID());
    	
        ResourceDto resourceDto = new ResourceDto(resources.getGold().getAmount(),
        										  resources.getTimber().getAmount(),
        										  resources.getClay().getAmount(),
        										  resources.getCorn().getAmount());
        
        City city = cityService.getCityByPlayerId(player.getId());
        List<ResourceBuilding> buildings = resourceBuildingService.allCityBuildings(city.getId());
        
        Map<Integer, ResourceBuilding> resourceBuildings = new  HashMap<>();
        Iterator<ResourceBuilding> itr=buildings.iterator();
        while(itr.hasNext())
        {
        	ResourceBuilding resourceBuilding= itr.next();
        	resourceBuildings.put(resourceBuilding.getPosition(),resourceBuilding);
        	
        }
    
        return CityResourcesDtoBuilder.builder()
                .resource(resourceDto)
                .resourceBuildings(resourceBuildings)
                .nickname(login)
                .build();
    }

    
}
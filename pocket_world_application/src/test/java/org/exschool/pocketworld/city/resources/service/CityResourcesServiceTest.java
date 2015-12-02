package org.exschool.pocketworld.city.resources.service;

import org.exschool.pocketworld.city.model.City;
import org.exschool.pocketworld.city.resources.builder.CityResourcesDtoBuilder;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.config.TestSpringConfig;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.resource.ResourceDto;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.building.service.ResourceBuildingService;
import org.exschool.pocketworld.resource.model.Resource;
import org.exschool.pocketworld.resource.service.ResourcesService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestSpringConfig.class)
public class CityResourcesServiceTest {
	@Autowired
    private PlayerService	playerService;
    @Autowired
    private ResourceBuildingService resourceBuildingService;
    @Autowired
    private ResourcesService resourcesService;
    @Autowired
    private CityService cityService;
    @Autowired
    CityResourcesService cityResourcesService;
    @Autowired
    CityResourcesBootstrap bootstrap;
    @Autowired
    Dao dao;
    

    @Before
    public void before() {
        bootstrap.fillDatabase();
    }

    @Test
    public void cityResourcesInfo() {
    	cityResourcesService.cityResourcesInfo();
    	
    }
    @Test
    public void Test(){
    	String login="login-1";
    	Player player= playerService.getPlayerByLogin(login);
    	
    	List<Resource> resources= resourcesService.getAllResourcesByUserId(player.getResourcesID());
    	
        ResourceDto resourceDto = 
        		new ResourceDto(resources.get(0).getAmount(),
        						resources.get(1).getAmount(),
        						resources.get(2).getAmount(),
        						resources.get(3).getAmount());
        
        City city = cityService.getCityByPlayerId(player.getId());
       
        Map<Integer, ResourceBuilding> resourceBuildings = resourceBuildings(city.getId());
  
        assertNotNull(CityResourcesDtoBuilder.builder()
                .resource(resourceDto)
                .resourceBuildings(resourceBuildings)
                .nickname(login)
                .build()); 
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
}



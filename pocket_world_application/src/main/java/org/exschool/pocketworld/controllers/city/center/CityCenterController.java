package org.exschool.pocketworld.controllers.city.center;


import org.exschool.pocketworld.building.model.Building;
import org.exschool.pocketworld.building.model.BuildingType;
import org.exschool.pocketworld.building.service.BuildingService;
import org.exschool.pocketworld.city.center.dto.CityCenterDto;
import org.exschool.pocketworld.city.center.service.CityCenterService;
import org.exschool.pocketworld.city.common.service.CommonCityService;
import org.exschool.pocketworld.city.model.City;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Set;


@Controller
@RequestMapping("/city/center")
public class CityCenterController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityCenterController.class);
    @Autowired
    private CityCenterService cityCenterService;
    @Autowired
    private CommonCityService commonCityService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private CityService cityService;

    private static final String PLAYER_NAME = "player-login"; //temporary

    @RequestMapping(value = "/addBuilding", method = RequestMethod.POST)
    public String addBuilding(@RequestParam String playerName, @RequestParam String type, @RequestParam int position) {
        if (cityCenterService.addBuilding(playerName, type, position)) {
            LOGGER.info("player - {} has built new building with type - {} in position - {}",
                    playerName, type, position);
            return "successMessage";
        } else {
            LOGGER.info("New building has not been created for player - {}, with type - {} in position - {}",
                    playerName, type, position);
            return "errorMessage";
        }
    }
    @RequestMapping(value = "/levelUp", method = RequestMethod.POST)
    public String levelUp(@RequestParam String playerName, @RequestParam int position) {
    	try {
    		Player currentPlayer = playerService.getPlayerByLogin(playerName);
    		City city = cityService.getCityByPlayerId(currentPlayer.getId());
    		Building building=  buildingService.getByCityIdPosition(city.getId(), position);
        	building.levelUp();
        	buildingService.save(building);
        	LOGGER.info("player - {} has upgrate building in position - {}",
        			playerName,position);
        	return "successMessage";
		} catch (Exception e) {
			LOGGER.info("Building has not been upgrated for player - {} in position - {}",
                    playerName, position);
			return "errorMessage";
		}
    	
    }
   
    @RequestMapping(value="/getInfo",method=RequestMethod.GET)
    @ResponseBody
    public String getType(@RequestParam String playerName, @RequestParam int position){
    	Player currentPlayer = playerService.getPlayerByLogin(playerName);
		City city = cityService.getCityByPlayerId(currentPlayer.getId());
		Building building=  buildingService.getByCityIdPosition(city.getId(), position);
		LOGGER.info("layer - {} get type of building in position - {}",
                playerName, position);
		int level =building.getLevel();
		BuildingType buildingType = building.getBuildingType();
		
		
    	return "["+
    	        "{ \"label\" : \"time\", \"value\" : "+
    	buildingService.getTimeByBuildingTypeLevel(buildingType, level)+" },"+
    	        "{ \"label\" : \"clay\", \"value\" : "+
    	buildingService.getResourceByBuildingTypeResourceTypeLevel(buildingType, ResourceType.CLAY, level)+" },"+
    	        "{ \"label\" : \"corn\", \"value\" : "+
    	buildingService.getResourceByBuildingTypeResourceTypeLevel(buildingType, ResourceType.CORN, level)+" },"+
    	        "{ \"label\" : \"gold\", \"value\" : "+
    	buildingService.getResourceByBuildingTypeResourceTypeLevel(buildingType, ResourceType.GOLD, level)+" },"+
    	        "{ \"label\" : \"timbet\", \"value\" : "+
    	buildingService.getResourceByBuildingTypeResourceTypeLevel(buildingType, ResourceType.TIMBER, level)+" }"+
    	        "]";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCityCenter(
            @RequestParam Map<String, String> allRequestParams, Model model) {

        LOGGER.info("Requested params:" + allRequestParams);
        CityCenterDto cityCenterDto = cityCenterService.cityCenterInfo(PLAYER_NAME);
        model.addAttribute("dto", cityCenterDto);
        Set<String> builtBuildingTypes = cityCenterDto.getBuildingTypes();
        model.addAttribute("buildingTypes", cityCenterService.availableForBuildBuildingTypes(builtBuildingTypes));
        LOGGER.info("Out:" + model);
        commonCityService.buildQueuedBuildings(PLAYER_NAME);
        return "city_center";
    }

     public void setCityCenterService(CityCenterService cityCenterService) {
        this.cityCenterService = cityCenterService;
    }

}

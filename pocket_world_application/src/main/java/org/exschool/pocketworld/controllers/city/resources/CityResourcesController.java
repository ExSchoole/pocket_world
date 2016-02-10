package org.exschool.pocketworld.controllers.city.resources;

import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.city.resources.service.CityResourcesService;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/city/resources")
public class CityResourcesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityResourcesController.class);
    @Autowired
    private CityResourcesService cityResourcesService;
    
    private static final String PLAYER_NAME = "player-login"; //temporary

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCityResources(
            @RequestParam Map<String, String> allRequestParams, Model model) {
        LOGGER.info("Requested params:" + allRequestParams);
        CityResourcesDto cityResourcesDto = cityResourcesService.cityResourcesInfo(PLAYER_NAME);
        model.addAttribute("dto", cityResourcesDto);
        LOGGER.info("Out:" + model);
        return "city_resources";
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getResourceBuildingsTypesList() {
        LOGGER.info("getResourceBuildingsList is invoked");
        return ResourceType.asListLowerCase();
    }

    @RequestMapping(value = "/buildings", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> createResourceBuilding(@RequestParam String playerName, 
    		@RequestParam String type, @RequestParam int position) {
    	LOGGER.info("player - {} has built new resourseBuilding with type - {} in position - {}",
                playerName, type, position);
        if (cityResourcesService.createResourceBuilding(playerName, type, position)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public void setCityResourcesService(CityResourcesService cityResourcesService) {
        this.cityResourcesService = cityResourcesService;
    }
    @RequestMapping(value = "/levelUp", method = RequestMethod.POST)
    public String levelUp(@RequestParam String playerName, @RequestParam int position) {
    	try {
    		cityResourcesService.levelUp(playerName, position);
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
    public List<Integer> getInfo(@RequestParam String playerName, @RequestParam int position){
    	
		
    	LOGGER.info("layer - {} get type of building in position - {}",
                playerName, position);
    	return cityResourcesService.getInfo(playerName, position);
    }
}

package org.exschool.pocketworld.controllers.city.resources;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.exschool.pocketworld.city.common.service.CommonCityService;
import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.city.resources.service.CityResourcesService;
import org.exschool.pocketworld.dto.BuildingInfo;
import org.exschool.pocketworld.dto.PositionOfBuilding;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/city/resources")
public class CityResourcesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityResourcesController.class);
    @Autowired
    private CityResourcesService cityResourcesService;
    @Autowired
    private CommonCityService commonCityService;

    private String playerName;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCityResources(
            @RequestParam Map<String, String> allRequestParams, Model model, Principal principal) {

        playerName = principal.getName();
        LOGGER.info("Requested params:" + allRequestParams);
        LOGGER.info("Player name:" + principal);
        commonCityService.buildQueuedBuildings(principal.getName());
        CityResourcesDto cityResourcesDto = cityResourcesService.cityResourcesInfo(playerName);
        model.addAttribute("dto", cityResourcesDto);
        model.addAttribute("time", new DateTime());
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
    public ResponseEntity<Void> createResourceBuilding(@RequestBody PositionOfBuilding positionOfBuilding) {
        LOGGER.info("RequestBody:" + positionOfBuilding);
        if (cityResourcesService.createResourceBuilding(positionOfBuilding, playerName)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/timeInfo", method = RequestMethod.GET)	
    @ResponseBody
    public int getTimeInfo(@RequestParam String type, @RequestParam int level){ 
     	return cityResourcesService.getTimeInfo(type.toUpperCase(), level);
    }
    
    @RequestMapping(value = "/checkResources", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Boolean> checkResources(
    		@RequestParam String playerName, @RequestParam String type, @RequestParam int level){
    	if (cityResourcesService.checkResources(playerName, type, level)) 
    		return new ResponseEntity<Boolean>(true, HttpStatus.OK) ;
    	else 
    		return new ResponseEntity<Boolean>(false, HttpStatus.I_AM_A_TEAPOT); 
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
    public BuildingInfo getInfo(@RequestParam String playerName, @RequestParam int position){
    	
		
    	LOGGER.info("layer - {} get type of building in position - {}",
                playerName, position);
    	return cityResourcesService.getInfo(playerName, position);
    }
}

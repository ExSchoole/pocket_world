package org.exschool.pocketworld.controllers.city.center;


import org.exschool.pocketworld.city.center.dto.CityCenterDto;
import org.exschool.pocketworld.city.center.service.CityCenterService;
import org.exschool.pocketworld.city.common.service.CommonCityService;
import org.exschool.pocketworld.dto.BuildingInfo;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
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

    private String playerName;

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


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCityCenter(
            @RequestParam Map<String, String> allRequestParams, Model model, Principal principal) {

        playerName = principal.getName();
        LOGGER.info("Requested params:" + allRequestParams);
        LOGGER.info("Player name:" + playerName);
        commonCityService.buildQueuedBuildings(principal.getName());
        CityCenterDto cityCenterDto = cityCenterService.cityCenterInfo(playerName);
        model.addAttribute("dto", cityCenterDto);
        model.addAttribute("time", new DateTime());
        Set<String> builtBuildingTypes = cityCenterDto.getBuildingTypes();
        model.addAttribute("buildingTypes", cityCenterService.availableForBuildBuildingTypes(builtBuildingTypes));
        LOGGER.info("Out:" + model);
        return "city_center";
    }

    @RequestMapping(value = "/checkResources", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Boolean> checkResources(
    		@RequestParam String playerName, @RequestParam String type, @RequestParam int level){
    	if (cityCenterService.checkResources(playerName, type, level)) 
    		return new ResponseEntity<Boolean>(true, HttpStatus.OK) ;
    	else 
    		return new ResponseEntity<Boolean>(false, HttpStatus.I_AM_A_TEAPOT); 
    }
    
     public void setCityCenterService(CityCenterService cityCenterService) {
        this.cityCenterService = cityCenterService;
    }
     
    @RequestMapping(value = "/timeInfo", method = RequestMethod.GET)	
    @ResponseBody
    public int getTimeInfo(@RequestParam String type, @RequestParam int level){ 
     	return cityCenterService.getTimeInfo(type.toUpperCase(), level);
    }

     @RequestMapping(value = "/levelUp", method = RequestMethod.POST)
     public String levelUp(@RequestParam String playerName, @RequestParam int position) {
     	try {
     		cityCenterService.levelUp(playerName, position);
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
     	
     	return cityCenterService.getInfo(playerName, position);
     }
}

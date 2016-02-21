package org.exschool.pocketworld.controllers;

import java.util.List;

import org.exschool.pocketworld.city.common.service.CommonCityService;
import org.exschool.pocketworld.dto.TimeOfBuilding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
    private CommonCityService commonCityService;
	
	private static final String PLAYER_NAME = "player-login"; //temporary
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCity() {
        return "redirect:/login";
    }
    
    @RequestMapping(value = "/getBuildingQueue", method = RequestMethod.GET)	
    @ResponseBody
    public List<TimeOfBuilding> getBuildingQueue(@RequestParam String playerName) { 
    	return commonCityService.getQueuedBuildings(PLAYER_NAME);
    }
    
    @RequestMapping(value = "/changeStatus", method = RequestMethod.GET)	
    @ResponseBody
    public void changeStatus(@RequestParam String playerName, int position, String type){ 
    	commonCityService.changeStatus(playerName, position, type);
    }
}

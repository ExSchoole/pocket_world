package org.exschool.pocketworld.controllers;

import java.util.Map;

import org.exschool.pocketworld.city.common.service.build.BuildService;
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
    private BuildService buildService;
	
	private static final String PLAYER_NAME = "player-login"; //temporary
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCity() {
        return "redirect:/city/center/";
    }

    @RequestMapping(value = "/getBuildingQueue", method = RequestMethod.GET)	
    @ResponseBody
    public Map<String, Map<Integer,Integer>> getBuildingQueue(@RequestParam String playerName) { 
    	return buildService.getQueuedBuildings(PLAYER_NAME);
    }
    
    @RequestMapping(value = "/changeStatus", method = RequestMethod.GET)	
    @ResponseBody
    public void getBuildingQueue(@RequestParam String playerName, int position, String type){ 
    	System.out.println(playerName+" "+position+" "+type);
    	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!"+ buildService.changeStatus(playerName, position, type));
    }
}

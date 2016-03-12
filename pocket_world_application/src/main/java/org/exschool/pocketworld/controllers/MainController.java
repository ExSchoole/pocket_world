package org.exschool.pocketworld.controllers;

import org.exschool.pocketworld.chat.model.Message;
import org.exschool.pocketworld.city.common.service.CommonCityService;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.dto.TimeOfBuilding;
import org.exschool.pocketworld.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
    private CommonCityService commonCityService;
	@Autowired
    private PlayerService playerService;
	@Autowired
	private CityService cityService;
	
	private static final String PLAYER_NAME = "player-login"; //temporary

    
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

    @RequestMapping(value ="/registration", method = RequestMethod.GET)
    public String showRegistrationPage() {
        return "registration";
    }

    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String showWelcomePage() {
        return "welcome";
       }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
    
    @RequestMapping(value ="/registerNewPlayer", method = RequestMethod.POST)
    @ResponseBody
    public String registerNewPlayer(@RequestParam String playerName,@RequestParam String password,
    		@RequestParam String cityName) {
    	
    	if (playerService.createPlayer(playerName.toLowerCase(),password)) {
    		cityService.createCity(playerService.getPlayerId(playerName.toLowerCase()), cityName);
            return "success";
        } else {
            
            return "error";
        }
       
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Message> sendMessage(@RequestParam String sender, @RequestParam String recipient,
                                               @RequestParam String message){

        Message messageObj = commonCityService.sendMessage(sender, recipient, message);
        if (messageObj != null) {
            return new ResponseEntity<Message>(messageObj, HttpStatus.OK);
        } else {
            return new ResponseEntity<Message>(messageObj, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/allMessages", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> sendMessage(@RequestParam String playerName){
        return commonCityService.getAllMessages(playerName);
    }
}

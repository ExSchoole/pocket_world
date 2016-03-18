package org.exschool.pocketworld.controllers;

import org.exschool.pocketworld.chat.model.Message;
import org.exschool.pocketworld.chat.model.UserRelation;
import org.exschool.pocketworld.city.common.service.CommonCityService;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.dto.TimeOfBuilding;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.util.comparator.BooleanComparator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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

    @RequestMapping(value = "/allMessagesBetweenTwoUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> allOldMessagesBetweenTwoUsers(@RequestParam String senderName,
                                                       @RequestParam String recipientName){
        return commonCityService.allMessagesBetweenTwoUsers(senderName, recipientName);
    }

    @RequestMapping(value = "/allUsersRelations", method = RequestMethod.GET)
    @ResponseBody
    public List<UserRelation> getAllUsersRelations(@RequestParam String playerName){
        return commonCityService.getAllUsersRelations(playerName);
    }

    @RequestMapping(value = "/changeMessageStatus", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Void> changeMessageStatus(@RequestParam String senderName,
                                                    @RequestParam String recipientName){
        commonCityService.changeMessageStatus(senderName, recipientName);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getAllUsersRelations(){
        List<Player> allPlayers = playerService.getAll();
        List<String> allNames = new ArrayList<>();
        for (Player s: allPlayers){
            allNames.add(s.getLogin());
        }
        return allNames;
    }

    @RequestMapping(value = "/checkNewMessages", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Boolean> changeMessageStatus(@RequestParam String playerName){
        return new ResponseEntity<Boolean>(commonCityService.checkNewMessages(playerName) ,HttpStatus.OK);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UserRelation> sendMessage(@RequestParam String playerName, @RequestParam String addingUser){

        UserRelation userRelation = commonCityService.addUser(playerName, addingUser);
        if (userRelation != null) {
            return new ResponseEntity<UserRelation>(userRelation, HttpStatus.OK);
        } else {
            return new ResponseEntity<UserRelation>(userRelation, HttpStatus.BAD_REQUEST);
        }
    }
}

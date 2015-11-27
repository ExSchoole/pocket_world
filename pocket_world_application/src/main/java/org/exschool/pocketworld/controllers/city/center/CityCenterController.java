package org.exschool.pocketworld.controllers.city.center;

import java.util.Map;

import org.exschool.pocketworld.city.center.dto.CityCenterDto;
import org.exschool.pocketworld.city.center.service.CityCenterService;
import org.exschool.pocketworld.player.builder.PlayerBuilder;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.model.PlayerResources;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.resource.ResourceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/city/center")
public class CityCenterController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityCenterController.class);
    @Autowired
    private CityCenterService cityCenterService;
    @Autowired
    private PlayerService playerService;
    private String playerLogin="user911";

    @RequestMapping(value = "/populate")
    public String populateDatabase(Model model) {
    	LOGGER.info(model.toString());
        Player player;
        PlayerResources playerResources = new PlayerResources(99,99,99,99);
        player=PlayerBuilder.builder().playerId(25L).login(playerLogin).playerResources(playerResources).build();
        playerService.savePlayer(player);
        // add initial Users data to Database
        return "init";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCityCenter(
            @RequestParam Map<String, String> allRequestParams, Model model) {
    	LOGGER.info("Requested params:" + allRequestParams);
        Player player = playerService.getPlayerByLogin(playerLogin);
        CityCenterDto cityCenterDto = cityCenterService.cityCenterInfo();
        cityCenterDto.setResourceDto(new ResourceDto(player.getPlayerResources().getGold(),
                                                     player.getPlayerResources().getTimber(),
                                                     player.getPlayerResources().getClay(),
                                                     player.getPlayerResources().getCorn()));
        model.addAttribute("dto", cityCenterDto);
        LOGGER.info("Out:" + model);
        return "city_center";
    }

    public void setCityCenterService(CityCenterService cityCenterService) {
        this.cityCenterService = cityCenterService;
    }

}

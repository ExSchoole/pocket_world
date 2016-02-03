package org.exschool.pocketworld.controllers.city.center;


import java.util.Map;
import java.util.Set;

import org.exschool.pocketworld.city.center.dto.CityCenterDto;
import org.exschool.pocketworld.city.center.service.CityCenterService;
import org.exschool.pocketworld.city.common.service.BuildService;
import org.exschool.pocketworld.player.service.PlayerService;
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
    private BuildService buildService;
    @Autowired
    private PlayerService playerService;

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


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCityCenter(
            @RequestParam Map<String, String> allRequestParams, Model model) {
        LOGGER.info("Requested params:" + allRequestParams);
        buildService.buildCompleted(playerService.getPlayerByLogin(PLAYER_NAME).getId());
        CityCenterDto cityCenterDto = cityCenterService.cityCenterInfo(PLAYER_NAME);
        model.addAttribute("dto", cityCenterDto);
        Set<String> builtBuildingTypes = cityCenterDto.getBuildingTypes();
        model.addAttribute("buildingTypes", cityCenterService.availableForBuildBuildingTypes(builtBuildingTypes));
        LOGGER.info("Out:" + model);
        return "city_center";
    }

     public void setCityCenterService(CityCenterService cityCenterService) {
        this.cityCenterService = cityCenterService;
    }

}

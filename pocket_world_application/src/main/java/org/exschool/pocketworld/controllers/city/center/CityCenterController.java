package org.exschool.pocketworld.controllers.city.center;

import java.util.Map;
import java.util.Set;

import org.exschool.pocketworld.building.BuildingInterim;
import org.exschool.pocketworld.city.center.dto.CityCenterDto;
import org.exschool.pocketworld.city.center.service.CityCenterService;
import org.exschool.pocketworld.city.model.City;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.model.PlayerResources;
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
    private CityService cityService;
    @Autowired
    private CityCenterService cityCenterService;
    @Autowired
    private PlayerService playerService;
    private String playerLogin = "test-user";
    private Player player;
    private PlayerResources playerResources;
    private City city;
    private String cityName = "test - cityName";

    @RequestMapping(value = "/addBuilding", method = RequestMethod.POST)
    public String addBuilding(@RequestParam String type, @RequestParam int position) {
        if (cityCenterService.addBuilding(city.getId(), position, new BuildingInterim(type, 1))) {
            LOGGER.info("new building with type - {} has been added to position - {}", type, position);
        } else LOGGER.info("Not added");

        return "city_center";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCityCenter(
            @RequestParam Map<String, String> allRequestParams, Model model) {
    	playerResources = new PlayerResources(100,100,100,100);
    	player = new Player(playerResources,playerLogin);
    	playerService.savePlayer(player);
    	
    	city = new City(player.getId(),cityName);
    	cityService.save(city);
    	
        LOGGER.info("Requested params:" + allRequestParams);
        CityCenterDto cityCenterDto = cityCenterService.cityCenterInfo();
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

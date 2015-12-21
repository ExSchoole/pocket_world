package org.exschool.pocketworld.controllers.city.resources;

import java.util.Map;

import org.exschool.pocketworld.city.model.City;
import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.city.resources.service.CityResourcesService;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.player.builder.PlayerBuilder;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.model.PlayerResources;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.util.builder.UserCityBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/city/resources")
public class CityResourcesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityResourcesController.class);
    @Autowired
    private CityResourcesService cityResourcesService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private CityService cityService;;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCityResources(
            @RequestParam Map<String, String> allRequestParams, Model model) {
        LOGGER.info("Requested params:" + allRequestParams);

        // -- temporary
        String playerLogin = "login-1";
        if(playerService.getPlayerByLogin(playerLogin) == null) {
            Player player = PlayerBuilder.builder()
                    .login("login-1")
                    .playerResources(new PlayerResources(1, 1, 1, 1))
                    .build();
            playerService.savePlayer(player);

            if (cityService.getCityByPlayerId(player.getId()) == null){
                City city = UserCityBuilder.builder()
                        .name("First City")
                        .playerId(player.getId())
                        .build();

                cityService.save(city);
              }
        }
        // -- end temporary

        CityResourcesDto cityResourcesDto = cityResourcesService.cityResourcesInfo();
        model.addAttribute("dto", cityResourcesDto);
        LOGGER.info("Out:" + model);
        return "city_resources";
    }

    public void setCityResourcesService(CityResourcesService cityResourcesService) {
        this.cityResourcesService = cityResourcesService;
    }
}

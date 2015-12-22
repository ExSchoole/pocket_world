package org.exschool.pocketworld.controllers.city.resources;

import org.exschool.pocketworld.city.model.City;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.dto.PositionOfBulding;
import org.exschool.pocketworld.player.builder.PlayerBuilder;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.model.PlayerResources;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.building.service.ResourceBuildingService;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.exschool.pocketworld.util.builder.ResourceBuildingBuilder;
import org.exschool.pocketworld.util.builder.UserCityBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/city/resources")
public class CityResourcesRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityResourcesRestController.class);

    @Autowired
    private ResourceBuildingService resourceBuildingService;

    @Autowired
    private CityService cityService;;

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value="/types",method=RequestMethod.GET)
    public List<String> getResourceBuildingsTypesList(){
        LOGGER.info("getResourceBuildingsList is invoked");
        return ResourceType.asListLowerCase();
    }

    @RequestMapping(value="/buildings", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createResourceBuilding(@RequestBody PositionOfBulding positionOfBulding){
        LOGGER.info("RequestBody:" + positionOfBulding);
        final Integer INITIAL_BUILDING_LEVEL = 0;

        // -- temporary
        String playerLogin = "login-1";
        Player player = playerService.getPlayerByLogin(playerLogin);
        City city = cityService.getCityByPlayerId(player.getId());
        // -- end temporary

        ResourceType resourceType = ResourceType.valueOf(positionOfBulding.getType().toUpperCase());


        ResourceBuilding resourceBuilding = ResourceBuildingBuilder.builder()
                               .buildingType(resourceType)
                               .cityId(city.getId())
                               .level(INITIAL_BUILDING_LEVEL)
                               .position(positionOfBulding.getPosition())
                               .build();

        resourceBuildingService.save(resourceBuilding);
    }
}

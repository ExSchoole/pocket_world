package org.exschool.pocketworld.controllers.city.resources;

import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.city.resources.service.CityResourcesService;
import org.exschool.pocketworld.dto.PositionOfBuilding;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/city/resources")
public class CityResourcesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityResourcesController.class);
    @Autowired
    private CityResourcesService cityResourcesService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCityResources(
            @RequestParam Map<String, String> allRequestParams, Model model) {
        LOGGER.info("Requested params:" + allRequestParams);
        CityResourcesDto cityResourcesDto = cityResourcesService.cityResourcesInfo();
        model.addAttribute("dto", cityResourcesDto);
        LOGGER.info("Out:" + model);
        return "city_resources";
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public List<String> getResourceBuildingsTypesList() {
        LOGGER.info("getResourceBuildingsList is invoked");
        return ResourceType.asListLowerCase();
    }

    @RequestMapping(value = "/buildings", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createResourceBuilding(@RequestBody PositionOfBuilding positionOfBuilding) {
        LOGGER.info("RequestBody:" + positionOfBuilding);
        if (cityResourcesService.createResourceBuilding(positionOfBuilding)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    public void setCityResourcesService(CityResourcesService cityResourcesService) {
        this.cityResourcesService = cityResourcesService;
    }
}

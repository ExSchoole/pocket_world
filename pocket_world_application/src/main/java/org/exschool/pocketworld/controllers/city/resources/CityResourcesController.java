package org.exschool.pocketworld.controllers.city.resources;

import java.util.Map;

import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.city.resources.service.CityResourcesService;
import org.exschool.pocketworld.resource.model.ResourceType;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCityResources(
            @RequestParam Map<String, String> allRequestParams, Model model) {
        LOGGER.info("Requested params:" + allRequestParams);

        CityResourcesDto cityResourcesDto = cityResourcesService.cityResourcesInfo();
        model.addAttribute("resourceTypes", ResourceType.asListLowerCase());
        model.addAttribute("dto", cityResourcesDto);
        LOGGER.info("Out:" + model);
        return "city_resources";
    }

    public void setCityResourcesService(CityResourcesService cityResourcesService) {
        this.cityResourcesService = cityResourcesService;
    }
}

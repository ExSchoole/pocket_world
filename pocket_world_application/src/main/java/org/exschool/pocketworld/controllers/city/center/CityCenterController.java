package org.exschool.pocketworld.controllers.city.center;

import java.util.Map;

import org.exschool.pocketworld.building.Building;
import org.exschool.pocketworld.city.center.dto.CityCenterDto;
import org.exschool.pocketworld.city.center.service.CityCenterService;
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
    private static final Logger LOGGEG = LoggerFactory.getLogger(CityCenterController.class);
    @Autowired
    private CityCenterService cityCenterService;

    @RequestMapping(value = "/addBuilding", method = RequestMethod.GET)
    public String addBuilding(@RequestParam String type, int position) {
    	LOGGEG.info(type);
    	LOGGEG.info(Integer.toString(position));   
    	cityCenterService.addBuilding(position, new Building(type,1)); 
    	return "city_center";
    }
    
    
    @RequestMapping(value = "/populate")
    public String populateDatabase(Model model) {
    	LOGGEG.info(model.toString());
        // add initial Users data to Database
        return "init";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showPeriphery(
            @RequestParam Map<String, String> allRequestParams, Model model) {
    	LOGGEG.info("Requested params:" + allRequestParams);

        CityCenterDto cityCenterDto = cityCenterService.cityCenterInfo();
        model.addAttribute("dto", cityCenterDto);
        LOGGEG.info("Out:" + model);
        return "city_center";
    }

    public void setCityCenterService(CityCenterService cityCenterService) {
        this.cityCenterService = cityCenterService;
    }
}

package org.exschool.pocketworld.city.center.controller;

import java.util.Map;

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
public class CityCenterController {
    private static final Logger logger = LoggerFactory.getLogger(CityCenterController.class);
    @Autowired
    private CityCenterService cityCenterService;

    @RequestMapping(value = "/")
    public String showIndexPage(Model model) {
        logger.info(model.toString());
        return "index";
    }

    @RequestMapping(value = "/populate")
    public String populateDatabase(Model model) {
        logger.info(model.toString());
        // add initial Users data to Database
        return "init";
    }

    @RequestMapping(value = "/city_center", method = RequestMethod.GET)
    public String showPeriphery(
            @RequestParam Map<String, String> allRequestParams, Model model) {
        logger.info("Requested params:" + allRequestParams);

        CityCenterDto CityCenterDTO = cityCenterService.cityCenterInfo();
        model.addAttribute("DTO", CityCenterDTO);
        logger.info("Out:" + model);
        return "city_center";
    }

    public void setCityCenterService(CityCenterService cityCenterService) {
        this.cityCenterService = cityCenterService;
    }
}

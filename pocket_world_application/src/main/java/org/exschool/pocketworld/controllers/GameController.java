package org.exschool.pocketworld.controllers;

import java.util.Map;
import java.util.Set;

import org.exschool.pocketworld.building.ArrayOfBuildings;
import org.exschool.pocketworld.dto.Dto;
import org.exschool.pocketworld.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GameController {
	private static final Logger logger = LoggerFactory.getLogger(GameController.class);

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
		logger.info("Out:"+ model);
		

		String nickName = "Nick"; //Player's name
		Resource resource = new Resource(100,200,300,400); //Resources
		ArrayOfBuildings buildings = new ArrayOfBuildings(); //ArrayOfBuildings
			
		Dto DTO = new Dto(buildings.getArrayOfBuildings(),resource,nickName);
		model.addAttribute("DTO", DTO);
						
		return "city_center";
	}
	

}

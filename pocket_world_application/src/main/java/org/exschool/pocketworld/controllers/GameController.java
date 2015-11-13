package org.exschool.pocketworld.controllers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Resource.Resource;


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
		
		Random r = new Random();
		Map<Integer,String> builds = new HashMap<Integer,String>(); //<position,type>
		
		for (int i=0; i<12; i++)
			builds.put(r.nextInt(12)+1, findHouse(r.nextInt(4)+1)); //заполн€ем фиктивными данными

		model.addAttribute("BuildObj", builds);
						
		return "city_center";
	}
	
	private String findHouse(int type){ //предполагаетс€ что в Ѕƒ типа здани€ ханитс€ как число
		                                //получаем путь к к картинке котора€ соответствует типа здани€
		String houseURL;
		
		switch(type){
		case 1: houseURL="images/house34.png"; break;
		case 2: houseURL="images/building78.png"; break;
		case 3: houseURL="images/factory6.png"; break;
		case 4: houseURL="images/farm.png"; break;
		
		default : houseURL="images/museum39.png"; break;
		}
		return houseURL;
	}

}

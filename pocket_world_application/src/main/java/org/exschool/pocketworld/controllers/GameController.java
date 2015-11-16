package org.exschool.pocketworld.controllers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import org.exschool.pocketworld.building.Building;
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
		
		String TypesOfBuildings[] = {"Mall", "Factory", "Market", "Shop"}; //Всевозможные типы зданий
		Random r = new Random();
		ArrayList<Building> builds = new ArrayList<Building>(); //Список зданий которые есть у игрока в БД, пока что у здания есть только позиция и тип
		
		for (int i=0; i<4; i++) 
			builds.add(new Building(i+1, TypesOfBuildings[r.nextInt(4)])); //Заполняем 4 первых клетки произвольными зданиями

		String nickName = "Nick"; //Имя игрока
		Resource resourse = new Resource(100,200,300,400); //Ресурсы 
		
		Dto MyDTO = new Dto(builds,resourse,nickName);
		model.addAttribute("DTO", MyDTO);
						
		return "city_center";
	}
	

}

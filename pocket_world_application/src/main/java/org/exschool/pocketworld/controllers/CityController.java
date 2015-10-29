package org.exschool.pocketworld.controllers;


import org.exschool.pocketworld.model.City;
import org.exschool.pocketworld.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CityController {
private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	CityService cityService;	
	
	@RequestMapping(value = "/city")
	public String listBooks(Model model){
		final int id = 1;
		City city = cityService.get(id);;
		logger.info("First city:" + model.toString());
		model.addAttribute("city",city);
		return "city";
	}

}

package org.exschool.pocketworld.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

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

	@RequestMapping(value = "/periphery", method = RequestMethod.GET)
	public String showPeriphery(
			@RequestParam Map<String, String> allRequestParams, Model model) {
		logger.info("Requested params:" + allRequestParams);
		logger.info("Out:"+ model);
		return "periphery";
	}

}

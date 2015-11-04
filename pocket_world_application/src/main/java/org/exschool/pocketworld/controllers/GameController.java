package org.exschool.pocketworld.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.resource.model.Resource;
import org.exschool.pocketworld.resource.model.ResourceBalance;
import org.exschool.pocketworld.resource.model.ResourceType;
import org.exschool.pocketworld.player.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {
	private static final Logger logger = LoggerFactory
			.getLogger(BookController.class);
	final private String RESOURCES = "resources";
	final private String LOGIN = "login";
	final private String ERROR_MSG = "errorMsg";
	final private String DEFAULT_PLAYER_LOGIN = "testPlayer1";

	@Autowired
	PlayerService playerService;

	@RequestMapping(value = "/")
	public String showIndexPage(Model model) {
		logger.info(model.toString());

		// add initial Users data to Database
		createPlayerWithResources(DEFAULT_PLAYER_LOGIN, Arrays.asList(1, 2, 3, 4));
		createPlayerWithResources("testPlayer2", Arrays.asList(10, 20, 30, 40));
		createPlayerWithResources("testPlayer3",Arrays.asList(100, 200, 300, 400));
		return "index";
	}

	@RequestMapping(value = "/city_center", method = RequestMethod.GET)
	public String showCityCenter(
			@RequestParam Map<String, String> allRequestParams, Model model) {
		String currentPlayerLogin = DEFAULT_PLAYER_LOGIN;
		logger.info("Requested params:" + allRequestParams);

		if (allRequestParams.containsKey(LOGIN)) {
			currentPlayerLogin = allRequestParams.get(LOGIN);
		}
		Player player = playerService.getPlayerByLogin(currentPlayerLogin);

		if (null == player) {
			model.addAttribute(RESOURCES, createListOfResourcesWithZeroAmount());
			model.addAttribute(ERROR_MSG,"No user with login "+currentPlayerLogin);
		} else {
			List<Resource> resources = player.getResourceBalance()
					.getResources();
			model.addAttribute(RESOURCES, resources);
			model.addAttribute(LOGIN,currentPlayerLogin);
		}
		logger.info("Out:"+ model);
		return "city_center";
	}
	
	@RequestMapping(value = "/periphery", method = RequestMethod.GET)
	public String showPeriphery(
			@RequestParam Map<String, String> allRequestParams, Model model) {
		String currentPlayerLogin = DEFAULT_PLAYER_LOGIN;
		logger.info("Requested params:" + allRequestParams);

		if (allRequestParams.containsKey(LOGIN)) {
			currentPlayerLogin = allRequestParams.get(LOGIN);
		}
		Player player = playerService.getPlayerByLogin(currentPlayerLogin);

		if (null == player) {
			model.addAttribute(RESOURCES, createListOfResourcesWithZeroAmount());
			model.addAttribute(ERROR_MSG,"No user with login "+currentPlayerLogin);
		} else {
			List<Resource> resources = player.getResourceBalance()
					.getResources();
			model.addAttribute(RESOURCES, resources);
			model.addAttribute(LOGIN,currentPlayerLogin);
		}
		logger.info("Out:"+ model);
		return "periphery";
	}

	private Resource createResource(ResourceType type, Integer amount) {
		Resource resource = new Resource();
		resource.setAmount(amount);
		resource.setResourceType(type);
		return resource;
	}

	private void createPlayerWithResources(String login,
			List<Integer> resourceAmount) {
		List<Resource> resources = new ArrayList<Resource>();
		resources.add(createResource(ResourceType.Clay, resourceAmount.get(0)));
		resources.add(createResource(ResourceType.Corn, resourceAmount.get(1)));
		resources.add(createResource(ResourceType.Gold, resourceAmount.get(2)));
		resources
				.add(createResource(ResourceType.Timber, resourceAmount.get(3)));

		ResourceBalance rBalance = new ResourceBalance();
		rBalance.setResources(resources);

		Player player = new Player();
		player.setLogin(login);
		player.setResourceBalance(rBalance);
		playerService.savePlayer(player);
	}

	private List<Resource> createListOfResourcesWithZeroAmount() {
		List<Resource> resources = new ArrayList<>();
		resources.add(new Resource(ResourceType.Clay, 0));
		resources.add(new Resource(ResourceType.Corn, 0));
		resources.add(new Resource(ResourceType.Gold, 0));
		resources.add(new Resource(ResourceType.Timber, 0));
		return resources;
	}

}

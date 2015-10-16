package org.exschool.pocketworld.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping(value = "/greeting")
	public String sayHello (Model model){
		
		model.addAttribute("greeting","Hello World 123");
		
		return "hello";
	}
}

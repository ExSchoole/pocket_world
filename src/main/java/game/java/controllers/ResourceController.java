package game.java.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import game.java.models.Resource;
import game.java.services.ResourceService;

@Controller
@RequestMapping(value="/resource")
public class ResourceController {
	@Autowired
	@Qualifier("resourceService")
	private ResourceService resourceService;
	
	public ResourceController(){}
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView modelAndView =new ModelAndView();
		List<Resource> resources= resourceService.getAll();
		modelAndView.addObject("resource",resources);
		modelAndView.setViewName("resource");
		return modelAndView;
	}
}

package org.exschool.pocketworld.controllers.city.center;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/")
public class CityController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCity() {
        
        return "redirect:/city/center/";
    }

}

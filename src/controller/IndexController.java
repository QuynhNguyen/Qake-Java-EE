package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/index")
public class IndexController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView indexPage(){
		return new ModelAndView("index");
	}
	
}

package controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;





@Controller

public class IndexController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexPage(Model model){
		model.addAttribute("title", "Welcome to Qake!");
		return "index";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logUserOut(Model model, HttpSession session){
		session.removeAttribute("User");
		return "logout";  

	}
}

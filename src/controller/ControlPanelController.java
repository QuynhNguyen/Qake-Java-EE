package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/controlpanel")
public class ControlPanelController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewControlPanel(Model model, HttpSession session){
		if(session.getAttribute("User") != null){
			return "controlpanel";
		}else{ 
			return "redirect:index.html"; 
		}
		
	}
	
	@RequestMapping(value = "/create-category", method = RequestMethod.GET)
	public String viewCreateCategory(Model model){
		return "create-category";
	}
	
	
}

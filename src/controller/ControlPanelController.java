package controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import model.Category;
import model.MyUser;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		model.addAttribute("title", "Create Category");
		model.addAttribute("category", new Category()); //Initiate default object for representation
		return "create-category";
	}
	
	@RequestMapping(value = "/create-category", method = RequestMethod.POST)
	public String createCategory(Model model, @ModelAttribute("category") @Valid Category category, Errors errors){
		
		model.addAttribute("title", "Create Category");
		
		
		if(errors.hasErrors()){
			model.addAttribute("category", category);
			return "create-category";
		}
		
		/*
		 * What to do if there is no error
		 * We need a DAO and Service to add category into the database
		 */
		
		return "create-category";
	}
	
	
}

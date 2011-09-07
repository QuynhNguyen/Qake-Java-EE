package controller;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import model.Category;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import service.category.CategoryServiceRemote;

@Controller
@RequestMapping(value="/controlpanel")
public class ControlPanelController {
	
	@EJB(mappedName="ejb/Category")
	CategoryServiceRemote categoryService;
	
	
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
	public String createCategory(Model model, @ModelAttribute("category") @Valid Category category, Errors errors, @RequestParam("name") String name, @RequestParam("description") String description){
		
		model.addAttribute("title", "Create Category");
		
		Category newCategory = new Category(HtmlUtils.htmlEscape(name), HtmlUtils.htmlEscape(description));
		
		if(errors.hasErrors()){
			model.addAttribute("category", category);
			return "create-category";
		}
		
		/*
		 * What to do if there is no error
		 * We need a DAO and Service to add category into the database
		 */
		try{
			categoryService.createCategory(newCategory);
			model.addAttribute("success", "<span class=\"success\">Category Created!</span>"); //Add success message
			category.setName(""); //Reset form field
			category.setDescription("");//Reset form field
		}catch(EJBException e){
			model.addAttribute("error", "<span class=\"error\">Category Name Already Existed</span>");
		}
		
		
		
		return "create-category";
	}
	
	
}

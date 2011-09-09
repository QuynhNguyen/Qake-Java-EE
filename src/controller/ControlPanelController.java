package controller;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.EJBTransactionRolledbackException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import model.Category;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
			model.addAttribute("success", "<div class=\"info\">Succesfully Created <p> <a href='manage-category.html'>Go back to Management Page</a></div>"); //Add success message
			category.setName(""); //Reset form field
			category.setDescription("");//Reset form field
		}catch(EJBException e){
			model.addAttribute("error", e.getMessage());
		}
		
		return "create-category";
	}
	
	@RequestMapping(value = "/manage-category", method = RequestMethod.GET)
	public String manageCategory(Model model){
		model.addAttribute("title", "Manage Category");
		model.addAttribute("categories", categoryService.getAllCategory());
		return "manage-category";
	}
	
	@RequestMapping(value = "/delete-category/{categoryId}", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable String categoryId, Model model){
		try{
			categoryService.deleteCategory(Integer.parseInt(HtmlUtils.htmlEscape(categoryId)));
			model.addAttribute("info", "<div class=\"info\">Succesfully Deleted <p> <a href='../manage-category.html'>Go back to Management Page</a></div>");
		}catch(EJBTransactionRolledbackException e){
			model.addAttribute("info", "<div class=\"info\">Category Doesn't Existed <p> <a href='../manage-category.html'>Go back to Management Page</a></div>");
		}
		
		return "manage-category";
	}
	
	@RequestMapping(value = "/edit-category/{categoryId}", method = RequestMethod.GET)
	public String editCategory(@PathVariable String categoryId, Model model){
		
		Category category = categoryService.getCategory(Integer.parseInt(HtmlUtils.htmlEscape(categoryId)));
		if(category == null){
			return "redirect:manage-category.html";
		}
		model.addAttribute("category", category);
		return "edit-category";
	}
	
	@RequestMapping(value = "/edit-category/{categoryId}", method = RequestMethod.POST)
	public String doEditCategory(@ModelAttribute("category") @Valid Category category, Errors errors, @PathVariable String categoryId, @RequestParam("name") String name, @RequestParam("description") String description, Model model){
		
		model.addAttribute("title", "Edit Category");
		
		if(errors.hasErrors()){
			model.addAttribute("category", category);
			return "edit-category";
		}
		
		category = categoryService.getCategory(Integer.parseInt(HtmlUtils.htmlEscape(categoryId)));
		
		if(category == null){
			return "redirect:manage-category.html";
		}
		category.setDescription(description);
		category.setName(name);
		
		categoryService.updateCategory(category);
		model.addAttribute("info", "<div class=\"info\">Succesfully Updated <p> <a href='../manage-category.html'>Go back to Management Page</a></div>");
		
		
		return "edit-category";
	}
	
	
}

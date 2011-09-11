package controller;

import java.util.HashSet;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import model.Category;
import model.Tweet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.HtmlUtils;

import service.category.CategoryServiceRemote;
import service.tweet.TweetServiceRemote;



@Controller
public class TweetController {
	
	@EJB(mappedName="ejb/Category")
	CategoryServiceRemote categoryService;
	
	@EJB(mappedName="ejb/Tweet")
	TweetServiceRemote tweetService;

	@RequestMapping(value = "/create-tweet", method = RequestMethod.GET)
	public String viewCreateTweet(Model model){
		List<Category> categories = categoryService.getAllCategory();
		model.addAttribute("title", "Create Tweet");
		model.addAttribute("tweet", new Tweet()); //Default tweet for representation later
		model.addAttribute("categories", categories); 
		return "create-tweet";
	}
	
	@RequestMapping(value = "/create-tweet", method = RequestMethod.POST)
	public String signUp(Model model, @ModelAttribute("tweet") @Valid Tweet tweet, Errors errors, HttpServletRequest request){
		
		Category aCategory = null; //Local category variable
		
		
		
		//Global Title
		model.addAttribute("title", "Create Tweet");
		
		//List of categories to display on the tweet creator form
		List<Category> categories = categoryService.getAllCategory();
		model.addAttribute("categories", categories); 
		
		//Create new HashSet to store categories;
		tweet.setCategories(new HashSet<Category>());
		
		/*
		 * Don't do anything if there is error
		 */
		if(errors.hasErrors()){
			model.addAttribute("tweet", tweet); //Add myuser object to redisplay input data
			model.addAttribute("categories", categories); 
			return "create-tweet"; //Redisplay signup page if there is any error
		}
		
		
		
		//Categories Checkbox selected
		String[] categoriesSelected = request.getParameterValues("categories_checkboxes");
		
		//if no checkbox selected then we use global news as default value
		if(categoriesSelected == null){
			categoriesSelected = new String[1];
			categoriesSelected[0] = "201";
		}
		
		//Time to store categories into tweet object
		try{
			for(int i = 0; i < categoriesSelected.length; i++){
				aCategory = categoryService.getCategory(Integer.parseInt(HtmlUtils.htmlEscape(categoriesSelected[i])));
				tweet.addCategory(aCategory);
			}		
		}catch(NullPointerException e){
			System.out.println("DAM, something went wrong again with stupid categories finder");
		}
		
		//Persist it!
		tweetService.createTweet(tweet);
		model.addAttribute("info", "<div class=\"info\">Succesfully Created Tweet<p> <a href='manage-tweet.html'>Go back to Management Page</a></div>"); //Add success message
		
		
		return "create-tweet"; 
	}
	
}

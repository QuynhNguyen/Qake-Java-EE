package controller;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import model.Category;
import model.MyUser;
import model.Tweet;

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
	public String createTweet(Model model, @ModelAttribute("tweet") @Valid Tweet tweet, Errors errors, HttpServletRequest request, HttpSession session){
		
		Category aCategory = null; //Local category variable
		
		//Global Title
		model.addAttribute("title", "Create Tweet");
		
		//List of categories to display on the tweet creator form
		List<Category> categories = categoryService.getAllCategory();
		model.addAttribute("categories", categories); 
		
		
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
			//Set author Name
			MyUser user = (MyUser) session.getAttribute("User");
			tweet.setAuthor(user.getEmail());
		}catch(NullPointerException e){
			System.out.println("DAM, something went wrong again with stupid categories finder");
		}
		
		//Persist it!
		tweetService.createTweet(tweet);
		model.addAttribute("info", "<div class=\"info\">Succesfully Created Tweet<p> <a href='pending-tweet.html'>Go back to Management Page</a></div>"); //Add success message
		
		return "create-tweet"; 
	}
	
	@RequestMapping(value = "/pending-tweet", method = RequestMethod.GET)
	public String viewPendingTweets(Model model){
		
		//Get All Pending Tweet
		List<Tweet> pendingTweets = tweetService.getAllPendingTweets();
		
		model.addAttribute("title", "Pending Tweets");
		model.addAttribute("pendingTweets", pendingTweets);
		
		return "pending-tweet";
	}
	
	/*
	 * WARNING: PLEASE DON'T EVER USE GET TO MODIFY/CHANGE A STATE OF YOUR APPLICATION
	 * MAIN REASON I AM DOING SO IN THIS APPLICATION BECAUSE I WANT TO SHOW THAT NO ONE
	 * LANGUAGE IS SUPERIOR OR INFERIOR THAN OTHER. IT'S ALL UP TO THE DEVELOPER THAT DESIGN
	 * THE APP. 
	 * 
	 * Take away Note: Understand the concept before you break it.
	 *                 Java is not superior or secure than other language like PHP
	 *                 Use POST when you try to modify or change a state of your app 
	 *                 
	 *  Why does it matter you ask? Ever heard of search engine crawler? :)
	 */
	@RequestMapping(value = "/{tweetId}/delete-tweet", method = RequestMethod.GET)
	public String deleteTweet(Model model, @PathVariable String tweetId){
		
		try{
			tweetService.deleteTweet(Integer.parseInt(HtmlUtils.htmlEscape(tweetId)));
			model.addAttribute("info", "<div class=\"info\">Succesfully Deleted <p> <a href='../pending-tweet.html'>Go back to Tweet Management Page</a></div>");
		}catch(Exception e){
			model.addAttribute("info", "<div class=\"info\">Failed To Deleted <p> <a href='../pending-tweet.html'>Go back to Tweet Management Page</a></div>");
		}
		
		
		model.addAttribute("title", "Delete Tweet");
		model.addAttribute("headerInfo", "Delete Tweet");
		
		return "generic";
	}
	
	/*
	 * WARNING: PLEASE DON'T EVER USE GET TO MODIFY/CHANGE A STATE OF YOUR APPLICATION
	 * MAIN REASON I AM DOING SO IN THIS APPLICATION BECAUSE I WANT TO SHOW THAT NO ONE
	 * LANGUAGE IS SUPERIOR OR INFERIOR THAN OTHER. IT'S ALL UP TO THE DEVELOPER THAT DESIGN
	 * THE APP. 
	 * 
	 * Take away Note: Understand the concept before you break it.
	 *                 Java is not superior or secure than other language like PHP
	 *                 Use POST when you try to modify or change a state of your app 
	 *                 
	 *  Why does it matter you ask? Ever heard of search engine crawler? :)
	 */
	@RequestMapping(value = "/{tweetId}/publish-tweet", method = RequestMethod.GET)
	public String publishTweet(Model model, @PathVariable String tweetId){
		
		
		model.addAttribute("title", "Publish Tweet");
		model.addAttribute("headerInfo", "Publish Tweet");
		try{
			tweetService.publishTweet(Integer.parseInt(HtmlUtils.htmlEscape(tweetId)));
			model.addAttribute("info", "<div class=\"info\">Succesfully Published Tweet To Company And Twitter <p> <a href='../pending-tweet.html'>Go back to Tweet Management Page</a></div>");
		}catch(Exception e){
			model.addAttribute("info", "<div class=\"info\">Failed To Publish Tweet! <p> <a href='../pending-tweet.html'>Go back to Tweet Management Page</a></div>");
		}
		

		return "generic";
	}
	
	@RequestMapping(value = "/{tweetId}/edit-tweet", method = RequestMethod.GET)
	public String viewTweetEdit(Model model, @PathVariable String tweetId){
		model.addAttribute("title", "Edit Tweet");
		model.addAttribute("tweet", tweetService.findTweetById(Integer.parseInt(HtmlUtils.htmlEscape(tweetId))));
		return "edit-tweet";
	}
	
	@RequestMapping(value = "/{tweetId}/edit-tweet", method = RequestMethod.POST)
	public String tweetEdit(Model model, @ModelAttribute("tweet") @Valid Tweet tweet, Errors errors, @RequestParam("content") String content, @PathVariable String tweetId){
		
		model.addAttribute("title", "Edit Tweet");
		
		if(errors.hasErrors()){
			model.addAttribute("tweet", tweet);
			return "edit-tweet";
		}
		
		//If there is no error
		tweetService.updateTweet(Integer.parseInt(HtmlUtils.htmlEscape(tweetId)), HtmlUtils.htmlEscape(content));
		model.addAttribute("info", "<div class=\"info\">Succesfully Updated Tweet <p> <a href='../pending-tweet.html'>Go back to Tweet Management Page</a></div>");
		return "edit-tweet";
	}
	
	public String searchTweet(Model model, @RequestParam("searchQueryString") String searchQueryString ){
		
		
		model.addAttribute("title", "Search Result");
		model.addAttribute("headerInfo", "Search Result");
		
		return "generic";
	}
	
	
}

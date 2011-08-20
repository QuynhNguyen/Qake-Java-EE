package controller;

import javax.ejb.EJB;
import javax.validation.Valid;

import model.MyUser;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.SignUpServiceRemote;


@Controller
@RequestMapping("/signup")
public class SignUpController {
	
	@EJB(name="SignUpService", mappedName="ejb/SignUp") 
	SignUpServiceRemote signUpService;

	
	@RequestMapping(method = RequestMethod.GET)
	public String showSignUpPage(Model model){
		model.addAttribute("title", "Sign Up"); //Browser Title
		model.addAttribute("myuser", new MyUser()); //Initiate default object for representation
		return "signup"; //Display signup.jsp page
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String signUp(Model model, @ModelAttribute("myuser") @Valid MyUser myuser, Errors errors){
		
		if(errors.hasErrors()){
			model.addAttribute("myuser", myuser); //Add myuser object to redisplay input data
			model.addAttribute("title", "Sign Up"); //Browser Title
			return "signup"; //Redisplay signup page if there is any error
		}
		
		signUpService.addUser(myuser); //If there is no error then goahead and add it to database
		model.addAttribute("title", "Welcome to Qake");
		return "index"; 
	}
	
}

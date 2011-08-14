package controller;

import javax.ejb.EJB;

import model.MyUser;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		model.addAttribute("title", "Sign Up");
		return "signup";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String signUp(Model model){
		signUpService.addUser(new MyUser("Dam@yahoo.com", "Got it", "finally", "hell ya"));
		return "signup";
	}
	
}

package controller;

import javax.ejb.EJB;
import javax.validation.Valid;

import model.MyUser;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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
		model.addAttribute("myuser", new MyUser());
		return "signup";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String signUp(Model model, @Valid MyUser myuser, Errors errors){
		
		if(errors.hasErrors()){
			model.addAttribute("myuser", myuser);
			return "signup";
		}
		
		signUpService.addUser(myuser);
		return "signup";
	}
	
}

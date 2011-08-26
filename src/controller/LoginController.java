package controller;

import javax.ejb.EJB;
import javax.persistence.NoResultException;

import model.MyUser;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import service.login.LoginServiceRemote;


@Controller
@RequestMapping("/login")
public class LoginController {

	@EJB(mappedName="ejb/Login")
	LoginServiceRemote loginService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String viewLoginForm(Model model){
		model.addAttribute("title", "login");
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String loginValidation(Model model, @RequestParam("email") String email, @RequestParam("password") String password){
		model.addAttribute("title", "login");
		MyUser user;
		
		try{
			user = loginService.loginValidation(email, password);
		}catch(Exception e){
			user = null;
		}
		
		if(user != null){
			return "index";
		}else{
			return "login";
		}
				
		
			
	}
}

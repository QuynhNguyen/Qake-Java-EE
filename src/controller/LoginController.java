package controller;

import javax.ejb.EJB;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	//Log the user in
	@RequestMapping(method=RequestMethod.POST)
	public String loginValidation(Model model, @RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, HttpServletResponse response){
		model.addAttribute("title", "login");
		MyUser user;
		
		try{
			//Attempt to log without using cookie
			user = loginService.loginValidation(email, password, false);
			//if success then store user into session
			session.setAttribute("User", user);
		}catch(Exception e){
			//if false then the user doesn't exist
			user = null;
		}
		
		//If the user is validated then set session and cookie for them
		if(user != null){
			Cookie qake_email = new Cookie("qake_email", user.getEmail());
			Cookie qake_password = new Cookie("qake_password", user.getPassword());
			qake_email.setMaxAge(60*60*24*30);
			qake_password.setMaxAge(60*60*24*30);
			response.addCookie(qake_email);
			response.addCookie(qake_password);
			return "index";
		}else{
			model.addAttribute("errors", "invalid email/pass");
			return "login";
		}
					
	}
}

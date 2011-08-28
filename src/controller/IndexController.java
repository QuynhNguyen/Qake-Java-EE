package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/index")
public class IndexController {

	@RequestMapping(method = RequestMethod.GET)
	public String indexPage(Model model){
		model.addAttribute("title", "Welcome to Qake!");
		return "index";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logUserOut(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		
		session.removeAttribute("LoginAttempt");
		session.removeAttribute("User");
		Cookie[] cookies = request.getCookies();
		response.setContentType("text/html");
		for(int i = 0; i < cookies.length; i++){
			cookies[i].setDomain(cookies[i].getDomain());
			cookies[i].setPath(cookies[i].getPath());
			cookies[i].setMaxAge(0);
			response.addCookie(cookies[i]);
		}
		
		return "redirect:../index.html";  

	}
}

package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/signup")
public class SignUpController {

	@RequestMapping(method = RequestMethod.GET)
	public String showSignUpPage(Model model){
		model.addAttribute("title", "Sign Up");
		return "signup";
	}
	
}

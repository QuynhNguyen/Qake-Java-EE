package interceptor;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MyUser;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import service.login.LoginServiceRemote;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception ex)
			throws Exception {

		
	}


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj, ModelAndView model) throws Exception {
		
		try{
			String foundEmailCookie = null;
			String foundPasswordCookie = null;
			
			//Grab Cookie from users's browsers
			Cookie[] cookie = request.getCookies();
			
			//Loop through an array of cookies and see if our cookies are in there :D 
			for(int i = 0; i < cookie.length; i++){
				if(foundEmailCookie == null && cookie[i].getName().equals("qake_email")){
					foundEmailCookie = cookie[i].getValue();
				}
				if(foundPasswordCookie == null && cookie[i].getName().equals("qake_password")){
					foundPasswordCookie = cookie[i].getValue();;
				}
				if(foundEmailCookie != null && foundPasswordCookie != null){
					try{
						Context jndi = new InitialContext();
						LoginServiceRemote service = (LoginServiceRemote) jndi.lookup("java:global/TwitterQake/LoginServiceImpl!service.login.LoginServiceRemote");
						MyUser user = service.loginValidation(foundEmailCookie, foundPasswordCookie, true);
						model.addObject("User", user);
						
						
					}catch(NamingException ex){
						System.out.println("Naming exception errors");
					}
				}
			}
			
		}catch(NullPointerException ex){
			return;
		}
		
		
		
		
		
		
		
	}

	
}

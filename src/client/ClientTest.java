package client;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import model.MyUser;
import service.SignUpService;
import service.login.LoginService;



public class ClientTest {

	/**
	 * @param args
	 */
	
	static Context jndi;
	
	public static void main(String[] args) {
		//testLoginValidation();

	}
	
	private static void testLoginValidation() {
		try{
			jndi = new InitialContext();
			LoginService service = (LoginService) jndi.lookup("java:global/TwitterQake/LoginServiceImpl!service.login.LoginServiceRemote");
			System.out.println(service.loginValidation("argothiusz@yahoo.com", "dontask"));
			
		}catch(NamingException e){
			System.out.println(e.getCause().toString());
		}
	}

	public static void testSignUpService(){
	
		
		try{
			jndi = new InitialContext();
			SignUpService service = (SignUpService) jndi.lookup("java:global/TwitterQake/SignUpServiceImpl!service.SignUpServiceRemote");
			service.addUser(new MyUser("Quynh@hotmail.com", "Quynh Nguyen", "Pass", "Salt"));
			
		}catch(NamingException e){
			System.out.println(e.getCause().toString());
		}
	}
	
	public static void testUniqueEmail(){
		
		
		try{
			jndi = new InitialContext();
			SignUpService service = (SignUpService) jndi.lookup("java:global/TwitterQake/SignUpServiceImpl!service.SignUpServiceRemote");
			System.out.println(service.isEmailUnique("quynhnguye5n003@gmail.com"));
			
		}catch(NamingException e){
			System.out.println(e.getCause().toString());
		}
	}

}

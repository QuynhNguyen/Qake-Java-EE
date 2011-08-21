package client;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import model.MyUser;
import service.SignUpService;



public class ClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//testUniqueEmail();

	}
	
	public static void testSignUpService(){
		Context jndi;
		
		try{
			jndi = new InitialContext();
			SignUpService service = (SignUpService) jndi.lookup("java:global/TwitterQake/SignUpServiceImpl!service.SignUpServiceRemote");
			service.addUser(new MyUser("Quynh@hotmail.com", "Quynh Nguyen", "Pass", "Salt"));
			
		}catch(NamingException e){
			System.out.println(e.getCause().toString());
		}
	}
	
	public static void testUniqueEmail(){
		Context jndi;
		
		try{
			jndi = new InitialContext();
			SignUpService service = (SignUpService) jndi.lookup("java:global/TwitterQake/SignUpServiceImpl!service.SignUpServiceRemote");
			System.out.println(service.isEmailUnique("quynhnguyen003@gmail.com"));
			
		}catch(NamingException e){
			System.out.println(e.getCause().toString());
		}
	}

}

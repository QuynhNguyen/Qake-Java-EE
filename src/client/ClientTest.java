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
		//testSignUpService();

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

}

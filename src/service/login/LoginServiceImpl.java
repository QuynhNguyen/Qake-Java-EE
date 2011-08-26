package service.login;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.MyUser;
import dao.MyUserDao;

@Stateless(mappedName="ejb/Login")
public class LoginServiceImpl implements LoginServiceRemote {

	@EJB
	MyUserDao userDao;
	
	@Override
	public MyUser loginValidation(String email, String password) {
		return userDao.LoginValidation(email, password);
	}

}

package service.login;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.springframework.web.util.HtmlUtils;

import model.MyUser;
import dao.MyUserDao;

@Stateless(mappedName="ejb/Login")
public class LoginServiceImpl implements LoginServiceRemote {

	@EJB
	MyUserDao userDao;
	
	@Override
	public MyUser loginValidation(String email, String password, boolean loginViaCookie) {
		return userDao.LoginValidation(HtmlUtils.htmlEscape(email), HtmlUtils.htmlEscape(password), loginViaCookie);
	}

}

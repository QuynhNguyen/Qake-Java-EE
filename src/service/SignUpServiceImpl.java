package service;

import helper.GeneralUtils;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.MyUser;
import org.springframework.web.util.HtmlUtils;
import dao.MyUserDao;

@Stateless(mappedName="ejb/SignUp") 
public class SignUpServiceImpl implements SignUpServiceLocal,
		SignUpServiceRemote {

	@EJB
	MyUserDao myUserDao;
	
	@Override
	public void addUser(MyUser user) {
		String salt = GeneralUtils.md5Hashing(Long.toString(new Date().getTime()) + user.getPassword());
		user.setSalt(salt);
		user.setPassword(GeneralUtils.md5Hashing(salt + user.getPassword()));
		user.setEmail(HtmlUtils.htmlEscape(user.getEmail()));
		user.setName(HtmlUtils.htmlEscape(user.getName()));
		myUserDao.addUser(user);
		
	}
	
	
 
	@Override
	public boolean isEmailUnique(String email) {
		return myUserDao.isEmailUnique(email);
	}



}

package service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import model.MyUser;
import dao.MyUserDao;

@Stateless
public class SignUpServiceImpl implements SignUpServiceLocal,
		SignUpServiceRemote {

	@EJB
	MyUserDao myUserDao;
	
	@Override
	public void addUser(MyUser user) {
		myUserDao.addUser(user);
	}

}

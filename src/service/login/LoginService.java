package service.login;

import model.MyUser;

public interface LoginService {
	public MyUser loginValidation(String email, String password, boolean loginViaCookie);
}

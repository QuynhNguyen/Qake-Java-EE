package service;

import model.MyUser;

public interface SignUpService {
	public void addUser(MyUser user);
	public boolean isEmailUnique(String email);
}

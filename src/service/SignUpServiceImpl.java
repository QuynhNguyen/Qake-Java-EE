package service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import model.MyUser;
import dao.MyUserDao;

@Stateless(mappedName="ejb/SignUp") 
public class SignUpServiceImpl implements SignUpServiceLocal,
		SignUpServiceRemote {

	@EJB
	MyUserDao myUserDao;
	
	@Override
	public void addUser(MyUser user) {
		String salt = Long.toString(new Date().getTime());
		user.setSalt(md5Hashing(salt + user.getPassword()));
		user.setPassword(md5Hashing(user.getPassword()));
		myUserDao.addUser(user);
		
	}
	
	public String md5Hashing(String value){
		
		try {
			//Initiate Java MD5 instance
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] byteArray = value.getBytes();
			digest.update(byteArray, 0, byteArray.length);
			BigInteger bigInt = new BigInteger(1, digest.digest());
			//Format bigInt into a string for storage
			return String.format("%1$032X", bigInt);
			
		} catch (NoSuchAlgorithmException e) {
			System.out.println("No Such Algorithm");
			return null;
		}
	}
 
	@Override
	public boolean isEmailUnique(String email) {
		return myUserDao.isEmailUnique(email);
	}



}

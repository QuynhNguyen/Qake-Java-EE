package model;

import javax.persistence.Entity;
import javax.persistence.Id;



@SuppressWarnings("serial")
@Entity
public class MyUser implements java.io.Serializable {

	@Id
	public String email;
	public String password;
	public String salt;
	public String name;
	

	public MyUser(){}
	
	public MyUser(String email, String name, String password, String salt) {
		this.email = email;
		this.password = password;
		this.salt = salt;
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}

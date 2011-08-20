package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;



@SuppressWarnings("serial")
@Entity
public class MyUser implements java.io.Serializable {

	@Id
	@NotEmpty
	@Email
	public String email;
	@NotEmpty
	@Length(min=5)
	public String password;
	@NotEmpty
	@Length(min=5)
	@Transient
	public String confirm;
	public String salt;
	@NotEmpty
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

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	
}

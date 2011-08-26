package dao;

import helper.GeneralUtils;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.MyUser;


@Stateless
public class MyUserDao {

	@PersistenceContext
	EntityManager em;
	
	public void addUser(MyUser user){
		em.persist(user);
	}
	
	public boolean isEmailUnique(String email){
		try{
		em.createQuery("SELECT myuser FROM MyUser myuser WHERE myuser.email = :email")
												  .setParameter("email", email).getSingleResult();
		return true;
		}catch(NoResultException e){
			return false;
		}
	}
	
	public MyUser LoginValidation(String email, String password){
		
		MyUser user = null;
		
		//First, we want to get the user salt string then concatenate it with the pass in password and hash it
		try{
			Query query = em.createQuery("SELECT myuser.salt FROM MyUser myuser WHERE myuser.email = :email");
			query.setParameter("email", email);
			String salt = (String) query.getSingleResult();
			password = GeneralUtils.md5Hashing(salt + password);
			
			//Second, we use the new hashed password to find our record in the database
			try{
				Query secondQuery = em.createQuery("SELECT myuser FROM MyUser myuser WHERE myuser.email = :email AND myuser.password = :password");
				secondQuery.setParameter("email", email);
				secondQuery.setParameter("password", password);
				user = (MyUser) secondQuery.getSingleResult();
				return user;
			}catch(NoResultException e){
				return user;
			}
		
		}catch(NoResultException e){
			return user;
		}
	}
	

	
}

package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

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
	
}

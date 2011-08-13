package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.MyUser;


@Stateless
public class MyUserDao {

	@PersistenceContext
	EntityManager em;
	
	public void addUser(MyUser user){
		em.persist(user);
	}
	
}

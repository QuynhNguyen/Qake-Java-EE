package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Category;


@Stateless
public class CategoryDao {
	
	@PersistenceContext
	EntityManager em;
	
	public void createCategory(Category category){
		em.persist(category);
	}

}

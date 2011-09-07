package dao;

import java.util.List;

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
	
	public List<Category> getAllCategory(){
		
		@SuppressWarnings("unchecked")
		List<Category> categories = (List<Category>) em.createQuery("SELECT category FROM Category category").getResultList();
		return categories;
		
	}
	
	public void deleteCategory(int id){
		em.remove(em.find(Category.class, id));
	}
	
	public Category getCategory(int id){
		return em.find(Category.class, id);
	}

}

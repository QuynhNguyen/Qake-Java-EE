package service.category;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import model.Category;
import dao.CategoryDao;

@Stateless(mappedName="ejb/Category")
public class CatagoryServiceImpl implements CategoryServiceLocal,
		CategoryServiceRemote {
	
	@EJB
	CategoryDao categoryDao;

	@Override
	public void createCategory(Category category) {
		categoryDao.createCategory(category);
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryDao.getAllCategory();
	}

	@Override
	public void deleteCategory(int category) {
		categoryDao.deleteCategory(category);
	}

	@Override
	public Category getCategory(int categoryId) {
		return categoryDao.getCategory(categoryId);
	} 

}

package service.category;

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

}

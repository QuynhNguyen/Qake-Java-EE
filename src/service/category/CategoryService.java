package service.category;

import java.util.List;

import model.Category;

public interface CategoryService {
	public void createCategory(Category category);
	public List<Category> getAllCategory();
}

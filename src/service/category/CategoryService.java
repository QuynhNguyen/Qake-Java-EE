package service.category;

import java.util.List;

import model.Category;

public interface CategoryService {
	public void createCategory(Category category);
	public void deleteCategory(int category);
	public List<Category> getAllCategory();
	public Category getCategory(int categoryId);
	public void updateCategory(Category category);
}

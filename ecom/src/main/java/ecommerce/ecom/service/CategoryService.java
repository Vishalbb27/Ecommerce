package ecommerce.ecom.service;

import java.util.List;

import ecommerce.ecom.dto.CategoryDto;

public interface CategoryService {
	public void createCategory(CategoryDto categoryDto);
	
	public void updateCategory(int id,CategoryDto categoryDto);
	
	public void deleteCategory(int id);
	
	public List<CategoryDto> getAllCategories();
}

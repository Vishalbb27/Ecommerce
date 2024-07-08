package ecommerce.ecom.mapper;

import ecommerce.ecom.dto.CategoryDto;
import ecommerce.ecom.entity.Category;

public class CategoryMapper {
	public static CategoryDto mapToCategoryDto(Category category) {
		CategoryDto categoryDto = new CategoryDto(category.getCategoryId(),category.getName(),category.getDescription());
		return categoryDto;
	}
	
	public static Category mapToCategory(CategoryDto category) {
		Category categoryDto = new Category(category.getName(),category.getDescription());
		return categoryDto;
	}
}

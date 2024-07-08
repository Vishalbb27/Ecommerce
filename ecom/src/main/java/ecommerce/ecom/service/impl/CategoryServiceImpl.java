package ecommerce.ecom.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ecommerce.ecom.dto.CategoryDto;
import ecommerce.ecom.entity.Category;
import ecommerce.ecom.exception.CategoryException;
import ecommerce.ecom.mapper.CategoryMapper;
import ecommerce.ecom.repository.CategoryRepository;
import ecommerce.ecom.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	private CategoryRepository categoryRepository;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void createCategory(CategoryDto categoryDto) {
		System.out.println("Inside Category");
		Category category = CategoryMapper.mapToCategory(categoryDto);
		categoryRepository.save(category);
	}

	@Override
	public void updateCategory(int id, CategoryDto categoryDto) {
		getCategoryById(id);
		Category category = CategoryMapper.mapToCategory(categoryDto);
		category.setCategoryId(id);
		categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(int id) {
		getCategoryById(id);
		categoryRepository.deleteById(id);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		return mapFromTaskToTaskDto(categories);
	}
	
	private List<CategoryDto> mapFromTaskToTaskDto(List<Category> categories) {
		return categories.stream().map(category -> CategoryMapper.mapToCategoryDto(category)).collect(Collectors.toList());
	}
	
	private void getCategoryById(int id) {
		Category category = categoryRepository.findById(id).stream().findFirst().orElse(null);
		if(category==null) {
			throw new CategoryException("Category not found.");
		}
	}

}

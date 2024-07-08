package ecommerce.ecom.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecom.dto.CategoryDto;
import ecommerce.ecom.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	private CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@PostMapping
	public ResponseEntity<?> createCategory(@RequestBody CategoryDto categoryDto){
		categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable("id") int id){
		categoryService.updateCategory(id,categoryDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getCategories(){
		List<CategoryDto> categoryDtos = categoryService.getAllCategories();
		return new ResponseEntity<>(categoryDtos,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") int id){
		categoryService.deleteCategory(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

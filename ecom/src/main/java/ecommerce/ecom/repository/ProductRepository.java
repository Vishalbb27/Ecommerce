package ecommerce.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.ecom.entity.Category;
import ecommerce.ecom.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findByCategory(Category category);
}

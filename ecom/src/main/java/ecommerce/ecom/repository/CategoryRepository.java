package ecommerce.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.ecom.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}

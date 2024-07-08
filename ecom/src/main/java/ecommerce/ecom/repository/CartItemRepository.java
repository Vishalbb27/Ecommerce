package ecommerce.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.ecom.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

}

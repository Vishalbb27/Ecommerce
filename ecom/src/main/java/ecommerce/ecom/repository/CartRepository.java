package ecommerce.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.ecom.entity.Cart;
import ecommerce.ecom.entity.User;

import java.util.List;


public interface CartRepository extends JpaRepository<Cart, Integer> {
	Cart findByUser(User user);
}

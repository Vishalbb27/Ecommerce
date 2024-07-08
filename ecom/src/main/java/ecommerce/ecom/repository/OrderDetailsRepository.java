package ecommerce.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.ecom.entity.OrderDetails;
import ecommerce.ecom.entity.User;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
	List<OrderDetails> findByUser(User user);
}

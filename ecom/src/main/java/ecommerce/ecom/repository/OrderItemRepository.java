package ecommerce.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.ecom.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}

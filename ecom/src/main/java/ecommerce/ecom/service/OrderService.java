package ecommerce.ecom.service;

import java.util.List;

import ecommerce.ecom.dto.OrderDetailsDto;

public interface OrderService {
	void createOrder(int userId);
	
	List<OrderDetailsDto> getOrdersByUserId(int userId);
	
	OrderDetailsDto getOrderById(int orderId);
	
	void updateOrderStatus(int orderId,String orderStatus);
	
	List<OrderDetailsDto> getAllOrders();
}

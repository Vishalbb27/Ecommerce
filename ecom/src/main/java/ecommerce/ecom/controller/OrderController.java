package ecommerce.ecom.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecom.dto.OrderDetailsDto;
import ecommerce.ecom.repository.OrderDetailsRepository;
import ecommerce.ecom.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	private OrderService orderService;
	
	
	
	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}

	@PostMapping("/user/{id}")
	public ResponseEntity<?> createOrder(@PathVariable("id") int id){
		orderService.createOrder(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getOrdersByUserId(@PathVariable("id") int id){
		 List<OrderDetailsDto> orderDetailsDtos= orderService.getOrdersByUserId(id);
		return new ResponseEntity<>(orderDetailsDtos,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateOrderStatus(@PathVariable("id") int id,@RequestParam("status") String status){
		orderService.updateOrderStatus(id, status);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> getOrders(){
		 List<OrderDetailsDto> orderDetailsDtos= orderService.getAllOrders();
		return new ResponseEntity<>(orderDetailsDtos,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOrderByOrderId(@PathVariable("id") int id){
		OrderDetailsDto orderDetailsDto = orderService.getOrderById(id);
		return new ResponseEntity<>(orderDetailsDto,HttpStatus.OK);
	}
}

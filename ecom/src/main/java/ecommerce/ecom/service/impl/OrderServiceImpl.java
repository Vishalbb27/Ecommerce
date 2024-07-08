package ecommerce.ecom.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.stereotype.Service;

import ecommerce.ecom.dto.CartDto;
import ecommerce.ecom.dto.CartItemDto;
import ecommerce.ecom.dto.OrderDetailsDto;
import ecommerce.ecom.dto.OrderItemDto;
import ecommerce.ecom.entity.Cart;
import ecommerce.ecom.entity.CartItem;
import ecommerce.ecom.entity.OrderDetails;
import ecommerce.ecom.entity.OrderItem;
import ecommerce.ecom.entity.Product;
import ecommerce.ecom.entity.User;
import ecommerce.ecom.mapper.CartItemMapper;
import ecommerce.ecom.mapper.OrderItemMapper;
import ecommerce.ecom.mapper.OrderMapper;
import ecommerce.ecom.mapper.ProductMapper;
import ecommerce.ecom.repository.CartRepository;
import ecommerce.ecom.repository.OrderDetailsRepository;
import ecommerce.ecom.repository.OrderItemRepository;
import ecommerce.ecom.repository.ProductRepository;
import ecommerce.ecom.repository.UserRepository;
import ecommerce.ecom.service.CartService;
import ecommerce.ecom.service.OrderService;
import net.bytebuddy.asm.Advice.Local;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderDetailsRepository orderDetailsRepository;
	private OrderItemRepository orderItemRepository;
	private CartRepository cartRepository;
	private UserRepository userRepository;
	private ProductRepository productRepository;

	public OrderServiceImpl(OrderDetailsRepository orderDetailsRepository, OrderItemRepository orderItemRepository,
			CartRepository cartRepository, UserRepository userRepository,ProductRepository productRepository) {
		super();
		this.orderDetailsRepository = orderDetailsRepository;
		this.orderItemRepository = orderItemRepository;
		this.cartRepository = cartRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
	}

	@Override
	public void createOrder(int userId) {
		User user = userRepository.findById(userId).get();
		if (user == null) {
			throw new RuntimeException();
		}

		Cart cart = cartRepository.findByUser(user);

		OrderDetails order = new OrderDetails();
		order.setUser(user);
		order.setOrderStatus("PENDING");

		BigDecimal totalPrice = BigDecimal.ZERO;
		BigDecimal totalDiscount = BigDecimal.ZERO;
		for (CartItem item : cart.getCarItems()) {
			BigDecimal orderItemPrice = item.getProduct().getPrice().multiply(item.getProduct().getDiscount())
					.multiply(BigDecimal.valueOf(item.getQuantity()));
			BigDecimal orderItemDiscount = item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
					.subtract(orderItemPrice);
			totalDiscount = totalDiscount.add(orderItemDiscount);
			totalPrice = totalPrice.add(orderItemPrice);
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderDetail(order);
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setPrice(totalPrice);
			orderItem.setTotal(totalPrice.subtract(totalDiscount));
			Product product = productRepository.findById(item.getProduct().getProductId()).get();
			product.setQuantity_sold(product.getQuantity_sold()+item.getQuantity());
			productRepository.save(product);
			if (order.getOrderItems() == null) {
				order.setOrderItems(new ArrayList<>());
			}
			order.getOrderItems().add(orderItem);
		}
		order.setTotalPrice(totalPrice);
		order.setTotalDiscount(totalDiscount);
		order.setOrderDate(LocalDate.now());
		order.setOrderTime(LocalTime.now());
		orderDetailsRepository.save(order);
		cartRepository.delete(cart);
	}

	@Override
	public List<OrderDetailsDto> getOrdersByUserId(int userId) {
		User user = userRepository.findById(userId).get();
		List<OrderDetails> orders = orderDetailsRepository.findByUser(user);
		List<OrderDetailsDto> orderDetails = orderDetailsDtoGenerator(orders);
		return orderDetails;
	}

	private List<OrderDetailsDto> orderDetailsDtoGenerator(List<OrderDetails> orders) {
		List<OrderDetailsDto> orderDetails = orders.stream().map(order -> {
			List<OrderItemDto> orderItemsDto = order.getOrderItems().stream().map(orderItem -> OrderItemMapper
					.mapToOrderItemDto(orderItem, ProductMapper.mapToProductDto(orderItem.getProduct())))
					.collect(Collectors.toList());

			return OrderMapper.mapToCartItemDto(order, orderItemsDto);
		}).collect(Collectors.toList());
		return orderDetails;
	}

	@Override
	public OrderDetailsDto getOrderById(int orderId) {

		OrderDetails order = orderDetailsRepository.findById(orderId).get();
		OrderDetailsDto detailsDto = OrderMapper
				.mapToCartItemDto(order,
						order.getOrderItems().stream()
								.map(orderItem -> OrderItemMapper.mapToOrderItemDto(orderItem,
										ProductMapper.mapToProductDto(orderItem.getProduct())))
								.collect(Collectors.toList()));
		return detailsDto;
	}

	@Override
	public void updateOrderStatus(int orderId, String orderStatus) {
		OrderDetails order = orderDetailsRepository.findById(orderId).get();
		order.setOrderStatus(orderStatus);
		orderDetailsRepository.save(order);

	}

	@Override
	public List<OrderDetailsDto> getAllOrders() {
		List<OrderDetails> orders = orderDetailsRepository.findAll();
		List<OrderDetailsDto> orderDetails = orderDetailsDtoGenerator(orders);
		return orderDetails;
	}

}

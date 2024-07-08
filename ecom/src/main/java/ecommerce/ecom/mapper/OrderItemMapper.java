package ecommerce.ecom.mapper;

import ecommerce.ecom.dto.CartItemDto;
import ecommerce.ecom.dto.OrderItemDto;
import ecommerce.ecom.dto.ProductDto;
import ecommerce.ecom.entity.CartItem;
import ecommerce.ecom.entity.OrderItem;

public class OrderItemMapper {
	public static OrderItemDto mapToOrderItemDto(OrderItem orderItem,ProductDto productDto) {
		OrderItemDto orderItemDto = new OrderItemDto(orderItem.getOrderItemId(),orderItem.getOrderDetail().getOrderId(),productDto,orderItem.getQuantity(),orderItem.getPrice(),orderItem.getTotal());
		return orderItemDto;
	}
}

package ecommerce.ecom.mapper;

import java.util.List;

import ecommerce.ecom.dto.OrderDetailsDto;
import ecommerce.ecom.dto.OrderItemDto;
import ecommerce.ecom.entity.OrderDetails;

public class OrderMapper {
	public static OrderDetailsDto mapToCartItemDto(OrderDetails orderDetails,List<OrderItemDto> orderItemDtos) {
		OrderDetailsDto orderDetailsDto = new OrderDetailsDto(orderDetails.getOrderId(),orderDetails.getUser().getId(),orderDetails.getOrderTime(),orderDetails.getOrderDate(),orderItemDtos,orderDetails.getTotalDiscount(),orderDetails.getTotalPrice(),orderDetails.getOrderStatus());
		return orderDetailsDto;
	}
}

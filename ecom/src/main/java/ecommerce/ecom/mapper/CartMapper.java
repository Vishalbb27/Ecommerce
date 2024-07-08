package ecommerce.ecom.mapper;

import java.util.List;

import ecommerce.ecom.dto.CartDto;
import ecommerce.ecom.dto.CartItemDto;

import ecommerce.ecom.entity.Cart;


public class CartMapper {
	public static CartDto mapToCartDto(Cart cart,List<CartItemDto> cartItemDtos) {
		CartDto cartDto = new CartDto(cart.getId(),cart.getUser().getId(),cartItemDtos);
		return cartDto;
	}
	
}

package ecommerce.ecom.mapper;

import ecommerce.ecom.dto.CartItemDto;
import ecommerce.ecom.dto.ProductDto;
import ecommerce.ecom.entity.CartItem;
import ecommerce.ecom.entity.Category;
import ecommerce.ecom.entity.Product;

public class CartItemMapper {
	public static CartItemDto mapToCartItemDto(CartItem cartItem,ProductDto productDto) {
		CartItemDto cartItemDto = new CartItemDto(cartItem.getCartItemId(), cartItem.getCart().getId(), productDto, cartItem.getQuantity());
		return cartItemDto;
	}
	
}

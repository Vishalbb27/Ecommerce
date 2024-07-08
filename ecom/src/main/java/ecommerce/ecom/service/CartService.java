package ecommerce.ecom.service;

import ecommerce.ecom.dto.CartDto;
import ecommerce.ecom.entity.Cart;

public interface CartService {
	public CartDto getCartItemsByUserId(int userId);
	public void addCartItem(int productId,int cartId);
	public void updateCartItemQuantity(int cartItemId,int quantity);
	public void deleteCartItem(int cartItemId);
}

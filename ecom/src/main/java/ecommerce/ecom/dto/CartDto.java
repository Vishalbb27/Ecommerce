package ecommerce.ecom.dto;

import java.util.List;

import ecommerce.ecom.entity.CartItem;


public class CartDto {
	
	private int cartId;

	private int user;

	private List<CartItemDto> cartDtos;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public List<CartItemDto> getCartDtos() {
		return cartDtos;
	}

	public void setCartDtos(List<CartItemDto> cartDtos) {
		this.cartDtos = cartDtos;
	}

	public CartDto(int cartId, int user, List<CartItemDto> cartDtos) {
		super();
		this.cartId = cartId;
		this.user = user;
		this.cartDtos = cartDtos;
	}

	public CartDto() {
		super();
	}
	
	
}

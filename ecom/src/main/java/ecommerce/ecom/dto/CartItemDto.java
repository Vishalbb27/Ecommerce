package ecommerce.ecom.dto;



public class CartItemDto {
	private int cartItemId;

	private int cart;

	private ProductDto product;

	private int quantity;

	public int getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	public int getCart() {
		return cart;
	}

	public void setCart(int cart) {
		this.cart = cart;
	}

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CartItemDto(int cartItemId, int cart, ProductDto product, int quantity) {
		super();
		this.cartItemId = cartItemId;
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
	}

	public CartItemDto() {
		super();
	}

	
	
	
}

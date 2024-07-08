package ecommerce.ecom.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ecommerce.ecom.dto.CartDto;
import ecommerce.ecom.dto.CartItemDto;
import ecommerce.ecom.entity.Cart;
import ecommerce.ecom.entity.CartItem;
import ecommerce.ecom.entity.Product;
import ecommerce.ecom.entity.User;
import ecommerce.ecom.exception.CartException;
import ecommerce.ecom.exception.UsernameNotFoundException;
import ecommerce.ecom.mapper.CartItemMapper;
import ecommerce.ecom.mapper.CartMapper;
import ecommerce.ecom.mapper.ProductMapper;
import ecommerce.ecom.repository.CartItemRepository;
import ecommerce.ecom.repository.CartRepository;
import ecommerce.ecom.repository.ProductRepository;
import ecommerce.ecom.repository.UserRepository;
import ecommerce.ecom.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	private CartItemRepository cartItemRepository;
	private CartRepository cartRepository;
	private UserRepository userRepository;
	private ProductRepository productRepository;

	public CartServiceImpl(CartItemRepository cartItemRepository, CartRepository cartRepository,
			UserRepository userRepository, ProductRepository productRepository) {
		super();
		this.cartItemRepository = cartItemRepository;
		this.cartRepository = cartRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
	}

	@Override
	public CartDto getCartItemsByUserId(int userId) {
		User user = userRepository.findById(userId).get();
		if (user == null) {
			throw new UsernameNotFoundException("User Id does not exist");
		}
		System.out.println("Inside cart");
		Cart cart = cartRepository.findByUser(user);

		if (cart == null) {
			Cart newcart = new Cart(user, null);
			cartRepository.save(newcart);
			return null;
		}

		List<CartItemDto> cartItemDtos = cart.getCarItems().stream().map(cartItem -> CartItemMapper
				.mapToCartItemDto(cartItem, ProductMapper.mapToProductDto(cartItem.getProduct())))
				.collect(Collectors.toList());

		return CartMapper.mapToCartDto(cart, cartItemDtos);

	}

	@Override
	public void addCartItem(int productId, int cartId) {
		Cart cart = cartRepository.findById(cartId).get();

		Product product = productRepository.findById(productId).get();

		CartItem cartItem = null;
		for (CartItem item : cart.getCarItems()) {
			if (item.getProduct().getProductId() == productId) {
				cartItem = item;
				break;
			}
		}
		if (cartItem == null) {
			cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setCart(cart);
		}
		cartItem.setQuantity(cartItem.getQuantity() + 1);
		cart.getCarItems().add(cartItem);
		cartRepository.save(cart);
	}

	@Override
	public void updateCartItemQuantity(int cartItemId, int quantity) {
		CartItem cartItem = cartItemRepository.findById(cartItemId).get();
		if (cartItem == null) {
			throw new CartException("Cart Item Not Found");
		}
		if(cartItem.getQuantity()+quantity>=1){
			cartItem.setQuantity(cartItem.getQuantity() + quantity);
			cartItemRepository.save(cartItem);
		}
		else {
			throw new CartException("You must buy atleast one product");
		}
		
	}

	@Override
	public void deleteCartItem(int cartItemId) {
		CartItem cartItem = cartItemRepository.findById(cartItemId).get();
		if (cartItem == null) {
			throw new CartException("Cart Item Not Found");
		}
		cartItemRepository.deleteById(cartItemId);
	}

}

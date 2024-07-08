package ecommerce.ecom.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecom.dto.CartDto;
import ecommerce.ecom.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	private CartService cartService;
	
	
	
	public CartController(CartService cartService) {
		super();
		this.cartService = cartService;
	}

	@PostMapping
	public ResponseEntity<?> addToCart(@RequestParam("cartId") int cartId,@RequestParam("productId") int productId){
		
		cartService.addCartItem(productId, cartId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getAllCartItems(@PathVariable("userId") int userId){
		 CartDto cartDto= cartService.getCartItemsByUserId(userId);
		return new ResponseEntity<>(cartDto,HttpStatus.OK);
	}
	
	@PutMapping("/{cartItemId}")
	public ResponseEntity<?> updateCartItemQuantity(@PathVariable("cartItemId") int cartItemId,@RequestParam("quantity") int quantity){
		cartService.updateCartItemQuantity(cartItemId,quantity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<?> deleteCartItem(@PathVariable("cartItemId") int cartItemId){
		cartService.deleteCartItem(cartItemId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

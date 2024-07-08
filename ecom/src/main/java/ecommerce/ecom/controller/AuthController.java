package ecommerce.ecom.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecom.dto.JtAuthResponse;
import ecommerce.ecom.dto.LoginDto;
import ecommerce.ecom.dto.RegisterDto;
import ecommerce.ecom.dto.UserDto;
import ecommerce.ecom.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private AuthService authService;


	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}
	
	@PostMapping("/register") 
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
		String response = authService.register(registerDto);
		return new ResponseEntity<String>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JtAuthResponse> login(@RequestBody LoginDto loginDto,HttpServletRequest request){
		CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrfToken != null) {
            String token = csrfToken.getToken();
            System.out.println(token	);
            // Validate token as needed
        }
		JtAuthResponse jwtAuthResponse = authService.login(loginDto);
		return new ResponseEntity<>(jwtAuthResponse,HttpStatus.OK);
	}
	
	@GetMapping("/user/{username}")
	public ResponseEntity<?> userDetails(@PathVariable("username") String username){
		UserDto userDto = authService.getUserDetails(username);
		
		return new ResponseEntity<>(userDto,HttpStatus.OK);
	}
	
}

package ecommerce.ecom.service;

import ecommerce.ecom.dto.JtAuthResponse;
import ecommerce.ecom.dto.LoginDto;
import ecommerce.ecom.dto.RegisterDto;
import ecommerce.ecom.dto.UserDto;

public interface AuthService {
String register(RegisterDto registerDto);
	
	JtAuthResponse login(LoginDto loginDto);
	
	UserDto getUserDetails(String username);
	
}

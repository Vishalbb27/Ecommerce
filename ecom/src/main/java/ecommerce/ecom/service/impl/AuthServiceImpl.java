package ecommerce.ecom.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.stereotype.Service;

import ecommerce.ecom.dto.JtAuthResponse;
import ecommerce.ecom.dto.LoginDto;
import ecommerce.ecom.dto.RegisterDto;
import ecommerce.ecom.dto.UserDto;
import ecommerce.ecom.entity.Role;
import ecommerce.ecom.entity.User;
import ecommerce.ecom.exception.APIException;
import ecommerce.ecom.mapper.UserMapper;
import ecommerce.ecom.repository.RoleRepository;
import ecommerce.ecom.repository.UserRepository;
import ecommerce.ecom.security.JwtHelper;
import ecommerce.ecom.service.AuthService;
import ecommerce.ecom.token.Token;
import ecommerce.ecom.token.TokenRepository;
import ecommerce.ecom.token.TokenType;



@Service
public class AuthServiceImpl implements AuthService {

	private UserRepository userRepository; 
	private RoleRepository roleRepository; 
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private JwtHelper jwtHelper;
	private UserDetailsService userDetailsService;
	private TokenRepository tokenRepository;


	public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtHelper jwtHelper, UserDetailsService userDetailsService,TokenRepository tokenRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtHelper = jwtHelper;
		this.userDetailsService = userDetailsService;
		this.tokenRepository = tokenRepository;
	}

	@Override
	public String register(RegisterDto registerDto) {
		// Check username is already exist or not
		if (userRepository.existsByUsername(registerDto.getUsername())) {
			throw new APIException(HttpStatus.BAD_REQUEST, "Username already exists");

		}

		if (userRepository.existsByEmail(registerDto.getEmail())) {
			throw new APIException(HttpStatus.BAD_REQUEST, "Email already exists!");
		}

		User user = new User();
		user.setName(registerDto.getName());
		user.setEmail(registerDto.getEmail());
		user.setUsername(registerDto.getUsername());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		Set<Role> roles = new HashSet();
		Role userRole = roleRepository.findByName("ROLE_USER");
		roles.add(userRole);

		user.setRoles(roles);

		userRepository.save(user);
		return "User Registered Successfully";
	}

	@Override
	public JtAuthResponse login(LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));// Implements
																												// authentication
		JtAuthResponse jwtAuthResponse = new JtAuthResponse();																										// method
		if(!authentication.isAuthenticated()) {
			throw new APIException(HttpStatus.NOT_FOUND, "Username does not exist please register.");
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
//		String token =jwtTokenProvider.generateToken(authentication);
		User userInfo = userRepository.findByUsername(loginDto.getUsernameOrEmail()).get();
		
		UserDetails userDetails= userDetailsService.loadUserByUsername(loginDto.getUsernameOrEmail());
		String jwttoken = jwtHelper.generateToken(userDetails);
		jwtAuthResponse.setAccessToken(jwttoken);
		revokeAllUserTokens(userInfo);
		saveUserToken(userInfo, jwttoken);
		
		
		String role = null;
		Optional<User> userOptional =userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail());
		if(userOptional.isPresent()) {
			User user= userOptional.get();
			Optional<Role> optionalRole = user.getRoles().stream().findFirst();
			if(optionalRole.isPresent()) {
				Role userRole = optionalRole.get();
				role = userRole.getName();
			}
		}
		
		jwtAuthResponse.setRole(role);
		return jwtAuthResponse;
	}

	private void saveUserToken(User userInfo, String jwttoken) {
		Token token = new Token(jwttoken,TokenType.BEARER,userInfo,false,false);
		tokenRepository.save(token);
	}
	
	private void revokeAllUserTokens(User user) {
		List<Token> validToken = tokenRepository.findAllValidTokenByUser(user.getId());
		if(validToken.isEmpty()) {
			return;
		}
		validToken.forEach(t-> {
			t.setExpired(true);
			t.setRevoked(true);
		});
		tokenRepository.saveAll(validToken);
	}



	@Override
	public UserDto getUserDetails(String username) {
		User user = userRepository.findByUsername(username).get();
		UserDto userDto = UserMapper.mapToUserDto(user);
		return userDto;
	}



}

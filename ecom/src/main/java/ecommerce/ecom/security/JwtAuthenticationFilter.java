package ecommerce.ecom.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import ecommerce.ecom.token.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private UserDetailsService userDetailsService;
	private JwtHelper helper;
	private TokenRepository tokenRepository;

	public JwtAuthenticationFilter( UserDetailsService userDetailsService,
			JwtHelper jwtHelper, TokenRepository tokenRepository) {
		super();
		this.userDetailsService = userDetailsService;
		this.helper = jwtHelper;
		this.tokenRepository = tokenRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		System.out.println(request.getHeader("Authorization"));
		// Get token

		String token = getTokenFromRequest(request);
		if (token != null) {
			String username = helper.getUsernameFromToken(token);
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			// Validate Token
			Boolean isTokenValid = tokenRepository.findByToken(token).map(t -> !t.isRevoked() && !t.isExpired()).orElse(false);
			if (StringUtils.hasText(token) && helper.validateToken(token, userDetails) && isTokenValid) {

				System.out.println(userDetails);
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response); 
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

}

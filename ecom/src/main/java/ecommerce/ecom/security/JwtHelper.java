package ecommerce.ecom.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.lang.Function;

@Component
public class JwtHelper {
	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-milliseconds}")
	private Long jwtExpirationDate;
	
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
		
	}

	private <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		SecretKey secretKey = generateSecretKey();
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
	}
	
	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String,Object> claims = new HashMap();
		return doGenerateToken(claims, userDetails.getUsername());
	}
	
	private SecretKey generateSecretKey() {
        byte[] apiKeySecretBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(apiKeySecretBytes, "HmacSHA256");
    }

	private String doGenerateToken(Map<String, Object> claims, String username) {
		// TODO Auto-generated method stub
		SecretKey secretKey = generateSecretKey();
		Date currentDate = new Date();
		Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
		return Jwts.builder().claims(claims).subject(username).issuedAt(new Date()).expiration(expireDate).signWith(secretKey).compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
}

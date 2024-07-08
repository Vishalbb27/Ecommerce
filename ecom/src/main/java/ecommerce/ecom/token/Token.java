package ecommerce.ecom.token;



import ecommerce.ecom.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Token {
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isRevoked() {
		return revoked;
	}

	public void setRevoked(boolean revoked) {
		this.revoked = revoked;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public Token() {
		super();
	}

	public Token(String token, TokenType tokenType, User user, boolean revoked, boolean expired) {
		super();
		this.token = token;
		this.tokenType = tokenType;
		this.user = user;
		this.revoked = revoked;
		this.expired = expired;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String token;
	
	@Enumerated(EnumType.STRING)
	private TokenType tokenType;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	  private boolean revoked;

	  private boolean expired;
}

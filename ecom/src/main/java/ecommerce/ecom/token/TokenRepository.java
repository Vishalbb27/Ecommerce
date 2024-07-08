package ecommerce.ecom.token;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TokenRepository extends JpaRepository<Token, Integer> {

	@Query("""

						select t from Token t inner join User u on t.user.id = u.id where u.id = :userId and (t.expired = false or t.revoked=false)
			""")
	List<Token> findAllValidTokenByUser(@Param("userId") int userId);

	Optional<Token> findByToken(String token);
}

package ecommerce.ecom.repository;




import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.ecom.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
Optional<User> findByUsername(String username);
	
	Boolean existsByEmail(String email);//Standard method existsBy
	
	Optional<User> findByUsernameOrEmail(String username,String email);
	
	Boolean existsByUsername(String username);
}

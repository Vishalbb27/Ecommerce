package ecommerce.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.ecom.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByName(String name);
	
}

package com.uol.compass.api_user.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uol.compass.api_user.entities.User;
import com.uol.compass.api_user.entities.User.Role;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
	
	Optional<User> findByUsername(String username);

	@Query("SELECT u.role FROM User u WHERE u.email = :email")
	Role findRoleByEmail(@Param("email") String email);
}

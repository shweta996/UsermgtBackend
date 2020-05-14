package com.qk.usermgt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qk.usermgt.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findById(String id);

	Optional<User> findByUsername(String username);

	Optional<User> findByUsernameAndEmail(String username, String email);

}

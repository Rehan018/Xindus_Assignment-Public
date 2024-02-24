package com.xinduswishlistmanagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xinduswishlistmanagement.Model.Users;

// Repository interface for Users entity
@Repository
public interface UserRepository extends JpaRepository<Users, String>{

	// Method to find a user by email
	public Optional<Users> findByEmail(String email);

}

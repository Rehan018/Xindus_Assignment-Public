package com.xinduswishlistmanagement.Service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xinduswishlistmanagement.Exception.UserException;
import com.xinduswishlistmanagement.Model.Users;
import com.xinduswishlistmanagement.Repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Register a new user
	public Users registerUser(Users users) throws UserException {
		Optional<Users> optUser = userRepository.findByEmail(users.getEmail());
		if (optUser.isPresent()) {
			throw new UserException("User already exists with email: " + users.getEmail());
		}
		// Generate a unique user ID
		users.setUserId(UUID.randomUUID().toString());
		// Encode the password before saving it to the database
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		// Save the user to the repository
		return userRepository.save(users);
	}
}

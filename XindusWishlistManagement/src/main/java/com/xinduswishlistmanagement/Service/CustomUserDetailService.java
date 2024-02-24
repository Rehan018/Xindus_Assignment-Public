package com.xinduswishlistmanagement.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xinduswishlistmanagement.Model.Users;
import com.xinduswishlistmanagement.Repository.UserRepository;

// Custom UserDetailsService implementation
@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	// Load user details by username (email)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Find user by email in the repository
		Users users = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
		return users;
	}

}

package com.xinduswishlistmanagement.Model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entity class representing users
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Users implements UserDetails {

	// Unique identifier for the user
	@Id
	private String userId;

	// User's first name
	private String firstName;

	// User's last name
	private String lastName;

	// User's email address (used as username)
	private String email;

	// User's password
	private String password;

	// One-to-one mapping with user's wishlist
	@OneToOne
	private Wishlist wishlist;

	// Implementation of UserDetails methods

	// Returns the authorities granted to the user
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	// Returns the username (email) of the user
	@Override
	public String getUsername() {
		return this.email;
	}

	// Checks if the user's account is not expired
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// Checks if the user's account is not locked
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// Checks if the user's credentials are not expired
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// Checks if the user is enabled
	@Override
	public boolean isEnabled() {
		return true;
	}

}

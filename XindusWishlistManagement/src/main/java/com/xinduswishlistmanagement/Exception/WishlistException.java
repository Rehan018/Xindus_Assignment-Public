package com.xinduswishlistmanagement.Exception;

// Custom exception class for handling errors related to wishlists
public class WishlistException extends Exception {

	// Default constructor
	public WishlistException() {

	}

	// Constructor with a message parameter
	public WishlistException(String message) {
		super(message);
	}

}

package com.xinduswishlistmanagement.Exception;

// Custom exception class for handling errors related to users
public class UserException extends Exception {

	// Default constructor
	public UserException() {

	}

	// Constructor with a message parameter
	public UserException(String message) {
		super(message);
	}

}

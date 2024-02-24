package com.xinduswishlistmanagement.Exception;

// Custom exception class for handling errors related to products
public class ProductException extends Exception {

	// Default constructor
	public ProductException() {

	}

	// Constructor with a message parameter
	public ProductException(String message) {
		super(message);
	}
}

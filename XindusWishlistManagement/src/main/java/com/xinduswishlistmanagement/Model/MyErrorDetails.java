package com.xinduswishlistmanagement.Model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Model class representing error details in the application
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyErrorDetails {

	// The timestamp when the error occurred
	private LocalDateTime timestamp;

	// The error message
	private String message;

	// Additional details about the error
	private String details;

}

package com.xinduswishlistmanagement.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Model class representing a JWT authentication request
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JWTRequest {

	// Email field of the user making the request
	private String email;

	// Password field of the user making the request
	private String password;

}

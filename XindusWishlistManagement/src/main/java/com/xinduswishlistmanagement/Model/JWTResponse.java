package com.xinduswishlistmanagement.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Model class representing a JWT authentication response
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JWTResponse {

	// The JWT token generated upon successful authentication
	private String jwtToken;

	// The username associated with the authenticated user
	private String username;
}

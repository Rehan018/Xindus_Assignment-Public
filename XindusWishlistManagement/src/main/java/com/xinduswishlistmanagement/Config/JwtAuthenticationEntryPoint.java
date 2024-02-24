package com.xinduswishlistmanagement.Config;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // Indicates that this class is a Spring component and should be automatically detected and registered during component scanning
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	// This method is called when a user tries to access a secured resource without proper authentication
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException authException) throws IOException, ServletException {
		// Set the HTTP status code to 401 (Unauthorized)
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		// Write an error message to the response body
		PrintWriter writer = response.getWriter();
		writer.println("Access Denied !! " + authException.getMessage());
	}
}

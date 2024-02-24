package com.xinduswishlistmanagement.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Indicates that this class contains bean definitions
public class SecurityConfig {

	@Autowired // Inject the JwtAuthenticationEntryPoint bean
	private JwtAuthenticationEntryPoint point;

	@Autowired // Inject the JwtAuthenticationFilter bean
	private JwtAuthenticationFilter filter;

	@Autowired // Inject the UserDetailsService bean
	private UserDetailsService userDetailsService;

	@Autowired // Inject the PasswordEncoder bean
	private PasswordEncoder passwordEncoder;

	// Define the security filter chain
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // Disable CSRF protection
				.cors(cors -> cors.disable()) // Disable CORS (Cross-Origin Resource Sharing) protection
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/api/**").authenticated() // Require authentication for API endpoints
						.requestMatchers("/auth/login").permitAll() // Allow access to the login endpoint without authentication
						.requestMatchers("/auth/register").permitAll() // Allow access to the register endpoint without authentication
						.anyRequest().authenticated()) // Require authentication for any other request
				.exceptionHandling(ex -> ex.authenticationEntryPoint(point)) // Set custom authentication entry point
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Set session creation policy to STATELESS
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class); // Add the JWT authentication filter before the UsernamePasswordAuthenticationFilter

		return http.build();
	}

	// Define the DAO authentication provider
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		return daoAuthenticationProvider;
	}
}

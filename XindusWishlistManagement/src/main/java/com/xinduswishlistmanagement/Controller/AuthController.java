package com.xinduswishlistmanagement.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xinduswishlistmanagement.Config.JwtHelper;
import com.xinduswishlistmanagement.Exception.UserException;
import com.xinduswishlistmanagement.Model.JWTRequest;
import com.xinduswishlistmanagement.Model.JWTResponse;
import com.xinduswishlistmanagement.Model.Users;
import com.xinduswishlistmanagement.Service.UserServiceImplementation;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // Autowired dependencies
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;

    @Autowired
    private UserServiceImplementation userService;

    // Logger for logging
    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    // Endpoint to register a new user
    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody Users users) throws UserException {
        Users user = userService.registerUser(users);
        return new ResponseEntity<Users>(user, HttpStatus.OK);
    }

    // Endpoint to authenticate and generate JWT token for login
    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody JWTRequest request) {

        // Authenticate user credentials
        this.doAuthenticate(request.getEmail(), request.getPassword());

        // Load user details and generate JWT token
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        // Create JWT response
        JWTResponse response = JWTResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Method to authenticate user credentials
    private void doAuthenticate(String email, String password) {

        // Create authentication token
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            // Perform authentication
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            // Handle authentication failure
            throw new BadCredentialsException("Invalid Username or Password  !!");
        }
    }

    // Exception handler for handling BadCredentialsException
    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}

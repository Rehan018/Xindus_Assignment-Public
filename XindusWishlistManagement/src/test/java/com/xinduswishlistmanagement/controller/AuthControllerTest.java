package com.xinduswishlistmanagement.controller;

import com.xinduswishlistmanagement.Config.JwtHelper;
import com.xinduswishlistmanagement.Controller.AuthController;
import com.xinduswishlistmanagement.Exception.UserException;
import com.xinduswishlistmanagement.Model.JWTRequest;
import com.xinduswishlistmanagement.Model.JWTResponse;
import com.xinduswishlistmanagement.Model.Users;
import com.xinduswishlistmanagement.Service.UserServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtHelper jwtHelper;

    @Mock
    private UserServiceImplementation userService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser_Success() throws UserException {
        Users mockUser = new Users();
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("testPassword");

        when(userService.registerUser(any(Users.class))).thenReturn(mockUser);
        ResponseEntity<Users> response = authController.register(mockUser);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUser, response.getBody());
    }

    @Test
    public void testLogin_Success() {
        JWTRequest mockRequest = new JWTRequest("test@example.com", "testPassword");
        UserDetails userDetails = new User("test@example.com", "testPassword", null);
        String mockToken = "mockToken";
        when(userDetailsService.loadUserByUsername("test@example.com")).thenReturn(userDetails);
        when(jwtHelper.generateToken(userDetails)).thenReturn(mockToken);
        ResponseEntity<JWTResponse> response = authController.login(mockRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockToken, response.getBody().getJwtToken());
        assertEquals("test@example.com", response.getBody().getUsername());
    }

    @Test
    public void testLogin_InvalidCredentials() {
        JWTRequest mockRequest = new JWTRequest("test@example.com", "wrongPassword");
        doThrow(BadCredentialsException.class).when(authenticationManager).authenticate(any());
        ResponseEntity<JWTResponse> response = authController.login(mockRequest);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}

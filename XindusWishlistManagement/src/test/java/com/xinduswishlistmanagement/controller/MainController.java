package com.xinduswishlistmanagement.controller;

import com.xinduswishlistmanagement.Controller.MainController;
import com.xinduswishlistmanagement.Exception.ProductException;
import com.xinduswishlistmanagement.Exception.UserException;
import com.xinduswishlistmanagement.Exception.WishlistException;
import com.xinduswishlistmanagement.Model.Product;
import com.xinduswishlistmanagement.Model.Wishlist;
import com.xinduswishlistmanagement.Service.ProductService;
import com.xinduswishlistmanagement.Service.WishlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class MainControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private WishlistService wishlistService;

    @InjectMocks
    private MainController mainController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addProductHandler_Success() throws ProductException {
        Product mockProduct = new Product();
        when(productService.addProduct(any(Product.class))).thenReturn(mockProduct);
        ResponseEntity<Product> responseEntity = mainController.addProductHandler(mockProduct);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockProduct, responseEntity.getBody());
    }

    @Test
    void addProductToWishlistHandler_Success() throws ProductException, WishlistException, UserException {
        Wishlist mockWishlist = new Wishlist();
        when(wishlistService.addProductToWishlist(anyInt())).thenReturn(mockWishlist);
        ResponseEntity<Wishlist> responseEntity = mainController.addProductToWishlistHandler(1);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockWishlist, responseEntity.getBody());
    }

    @Test
    void deleteProductFromWishlistHandler_Success() throws ProductException, WishlistException, UserException {
        Wishlist mockWishlist = new Wishlist();
        when(wishlistService.deleteProductFromWishlist(anyInt())).thenReturn(mockWishlist);
        ResponseEntity<Wishlist> responseEntity = mainController.deleteProductFromWishlistHandler(1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockWishlist, responseEntity.getBody());
    }

    @Test
    void getLoggedInUserWishlistHandler_Success() throws ProductException, WishlistException, UserException {
        Wishlist mockWishlist = new Wishlist();
        when(wishlistService.getLoggedInUserWishlist()).thenReturn(mockWishlist);
        ResponseEntity<Wishlist> responseEntity = mainController.getLoggedInUserWishlistHandler();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockWishlist, responseEntity.getBody());
    }
}

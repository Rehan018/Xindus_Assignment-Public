package com.xinduswishlistmanagement.Service;

import com.xinduswishlistmanagement.Exception.ProductException;
import com.xinduswishlistmanagement.Exception.UserException;
import com.xinduswishlistmanagement.Exception.WishlistException;
import com.xinduswishlistmanagement.Model.Wishlist;

public interface WishlistService {

	// Add a product to the wishlist
	public Wishlist addProductToWishlist(Integer productId) throws WishlistException, UserException, ProductException;

	// Delete a product from the wishlist
	public Wishlist deleteProductFromWishlist(Integer productId) throws WishlistException, UserException, ProductException;

	// Get the wishlist of the logged-in user
	public Wishlist getLoggedInUserWishlist() throws WishlistException, UserException, ProductException;
}

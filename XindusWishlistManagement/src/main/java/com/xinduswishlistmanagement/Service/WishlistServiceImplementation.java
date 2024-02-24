package com.xinduswishlistmanagement.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.xinduswishlistmanagement.Exception.ProductException;
import com.xinduswishlistmanagement.Exception.UserException;
import com.xinduswishlistmanagement.Exception.WishlistException;
import com.xinduswishlistmanagement.Model.Product;
import com.xinduswishlistmanagement.Model.Users;
import com.xinduswishlistmanagement.Model.Wishlist;
import com.xinduswishlistmanagement.Repository.ProductRepository;
import com.xinduswishlistmanagement.Repository.UserRepository;
import com.xinduswishlistmanagement.Repository.WishlistRepository;

@Service
public class WishlistServiceImplementation implements WishlistService{

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private WishlistRepository wishlistRepository;

	// Adds a product to the wishlist
	@Override
	public Wishlist addProductToWishlist(Integer productId) throws WishlistException, UserException, ProductException {
		String loggedInUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

		// Check if the user exists
		Optional<Users> opt = userRepository.findByEmail(loggedInUserEmail);
		if(opt.isEmpty()) {
			throw new UserException("User not found");
		}

		// Check if the product exists
		Optional<Product> optProduct = productRepository.findById(productId);
		if(optProduct.isEmpty()) {
			throw new ProductException("Product not found");
		}
		Product product = optProduct.get();
		Users loggedInUser = opt.get();

		// Get or create the user's wishlist
		Wishlist wishlist = loggedInUser.getWishlist();
		if (wishlist == null) {
			wishlist = new Wishlist();
			wishlistRepository.save(wishlist);
			loggedInUser.setWishlist(wishlist);
		}

		// Add the product to the wishlist
		wishlist.getListOfProducts().add(product);
		userRepository.save(loggedInUser);
		return wishlist;
	}

	// Deletes a product from the wishlist
	@Override
	public Wishlist deleteProductFromWishlist(Integer productId) throws WishlistException, UserException, ProductException {
		String loggedInUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

		// Check if the user exists
		Optional<Users> opt = userRepository.findByEmail(loggedInUserEmail);
		if(opt.isEmpty()) {
			throw new UserException("User not found");
		}

		// Check if the product exists
		Optional<Product> optProduct = productRepository.findById(productId);
		if(optProduct.isEmpty()) {
			throw new ProductException("Product not found");
		}

		Product product = optProduct.get();
		Users loggedInUser = opt.get();

		// Get the user's wishlist
		Wishlist wishlist = loggedInUser.getWishlist();
		if (wishlist == null) {
			throw new WishlistException("Wishlist not found");
		}

		// Remove the product from the wishlist
		wishlist.getListOfProducts().remove(product);

		userRepository.save(loggedInUser);

		return wishlist;
	}

	// Retrieves the wishlist of the logged-in user
	@Override
	public Wishlist getLoggedInUserWishlist() throws WishlistException, UserException, ProductException {
		String loggedInUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

		// Check if the user exists
		Optional<Users> opt = userRepository.findByEmail(loggedInUserEmail);
		if(opt.isEmpty()) {
			throw new UserException("User not found");
		}
		Users loggedInUser = opt.get();

		// Get the user's wishlist
		Wishlist wishlist = loggedInUser.getWishlist();
		return wishlist;
	}
}

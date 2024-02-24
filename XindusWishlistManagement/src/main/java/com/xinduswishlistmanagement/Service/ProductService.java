package com.xinduswishlistmanagement.Service;

import java.util.List;

import com.xinduswishlistmanagement.Exception.ProductException;
import com.xinduswishlistmanagement.Model.Product;

// Interface for ProductService
public interface ProductService {

	// Add a new product
	public Product addProduct(Product product) throws ProductException;

	// Delete a product by ID
	public Product deleteProduct(Integer productId) throws ProductException;

	// Update the price of a product by ID
	public Product updateProduct(Integer productId, Integer productPrice) throws ProductException;

	// View details of a product by ID
	public Product viewProduct(Integer productId) throws ProductException;

	// View details of all products
	public List<Product> viewAllProducts() throws ProductException;

}

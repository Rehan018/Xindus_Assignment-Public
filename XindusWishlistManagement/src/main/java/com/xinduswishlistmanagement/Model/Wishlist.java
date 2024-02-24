package com.xinduswishlistmanagement.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entity class representing wishlists
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Wishlist {

	// Unique identifier for the wishlist
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer wishlistId;

	// One-to-many mapping with products in the wishlist
	@OneToMany(cascade = CascadeType.ALL)
	private List<Product> listOfProducts = new ArrayList<>();
}

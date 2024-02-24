package com.xinduswishlistmanagement.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entity class representing a product
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

	// Unique identifier for the product
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;

	// Name of the product
	private String productName;

	// Price of the product
	private Integer productPrice;

	// Information or description about the product
	private String productInformation;

}

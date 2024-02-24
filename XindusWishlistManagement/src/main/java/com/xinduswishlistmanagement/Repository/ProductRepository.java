package com.xinduswishlistmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xinduswishlistmanagement.Model.Product;

// Repository interface for Product entity
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}

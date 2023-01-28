package com.ty.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.shopapp.dto.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByName(String name);

	//@Query(value = "FROM Product WHERE name=?1")
	@Query(value="SELECT * FROM product_table WHERE product_name = ?",nativeQuery = true)
	Product getProductByName(String name);

}

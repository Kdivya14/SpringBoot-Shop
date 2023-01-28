package com.ty.shopapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.shopapp.dto.Product;
import com.ty.shopapp.repository.ProductRepository;

@Repository
public class ProductDao {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product addProduct(Product newProduct) {
		Product savedProduct = productRepository.save(newProduct);
		return savedProduct;
	}

	public Product getProduct(int id) {
		Optional<Product> optional = productRepository.findById(id);
//		if(optional.isEmpty())
//			return null;
//		return optional.get();
		if(optional.isPresent())
			return optional.get();
		return null;
	}
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product updateProduct(Product productToBeUpdated) {
		return productRepository.save(productToBeUpdated);
	}

	public void deleteProduct(Product productToBeDeleted) {
		productRepository.delete(productToBeDeleted);
	}

	public Product getProductByName(String name) {
		//Product searchedProduct = productRepository.findByName(name);
		Product searchedProduct = productRepository.getProductByName(name);
		return searchedProduct;
	}
}

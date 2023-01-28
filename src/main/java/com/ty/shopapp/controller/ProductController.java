package com.ty.shopapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.shopapp.constants.ApplicationConstants;
import com.ty.shopapp.dto.Product;
import com.ty.shopapp.response.ResponseStructure;
import com.ty.shopapp.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/product")
	public ResponseEntity<ResponseStructure<Product>> addProduct(@RequestBody Product newProduct) {
		Product savedProduct = productService.saveProduct(newProduct);
		if (savedProduct != null) {
			ResponseStructure<Product> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage(ApplicationConstants.PRODUCT_ADDED);
			responseStructure.setData(savedProduct);

			ResponseEntity<ResponseStructure<Product>> responseEntity = new ResponseEntity<ResponseStructure<Product>>(
					responseStructure, HttpStatus.CREATED);
			return responseEntity;
		} else {
			ResponseStructure<Product> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage(ApplicationConstants.PRODUCT_NOT_ADDED);
			responseStructure.setData(null);

			ResponseEntity<ResponseStructure<Product>> responseEntity = new ResponseEntity<ResponseStructure<Product>>(
					responseStructure, HttpStatus.BAD_REQUEST);
			return responseEntity;
		}
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<ResponseStructure<Product>> searchProduct(@PathVariable("productId") int id) {
		Product searchedProduct = productService.getProduct(id);
		if (searchedProduct != null) {
			ResponseStructure<Product> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage(ApplicationConstants.PRODUCT_FOUND);
			responseStructure.setData(searchedProduct);

			ResponseEntity<ResponseStructure<Product>> responseEntity = new ResponseEntity<ResponseStructure<Product>>(
					responseStructure, HttpStatus.FOUND);
			return responseEntity;
		} else {
			ResponseStructure<Product> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage(ApplicationConstants.PRODUCT_NOT_FOUND + id);
			responseStructure.setData(searchedProduct);

			ResponseEntity<ResponseStructure<Product>> responseEntity = new ResponseEntity<ResponseStructure<Product>>(
					responseStructure, HttpStatus.OK);
			return responseEntity;
		}
	}

	@GetMapping("/products")
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProcucts() {
		List<Product> allProducts = productService.getAllProducts();
		ResponseStructure<List<Product>> responseStructure = new ResponseStructure<>();
		if (allProducts.isEmpty()) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage(ApplicationConstants.NO_PRODUCTS);
			responseStructure.setData(null);
		} else {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage(ApplicationConstants.ALL_PRODUCTS);
			responseStructure.setData(allProducts);
		}
		ResponseEntity<ResponseStructure<List<Product>>> responseEntity = new ResponseEntity<>(responseStructure,
				HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("/product/{productId}")
	public ResponseEntity<ResponseStructure<Product>> updateProducts(@PathVariable("productId") int id,
			@RequestBody Product product) {
		Product updatedProduct = productService.updateProduct(id, product);
		ResponseStructure<Product> responseStructure = new ResponseStructure<>();
		if (updatedProduct != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage(ApplicationConstants.PRODUCT_UPDATED);
			responseStructure.setData(updatedProduct);
		} else {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage(ApplicationConstants.PRODUCT_NOT_FOUND);
			responseStructure.setData(null);
		}
		ResponseEntity<ResponseStructure<Product>> responseEntity = new ResponseEntity<ResponseStructure<Product>>(
				responseStructure, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("/product/{productId}")
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(@PathVariable("productId") int productId) {
		Product deletedProduct = productService.deleteProduct(productId);
		ResponseStructure<Product> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		if (deletedProduct != null) {
			responseStructure.setMessage(ApplicationConstants.PRODUCT_DELETED);
			responseStructure.setData(deletedProduct);
		} else {
			responseStructure.setMessage(ApplicationConstants.PRODUCT_NOT_FOUND);
			responseStructure.setData(null);
		}
		ResponseEntity<ResponseStructure<Product>> responseEntity = new ResponseEntity<ResponseStructure<Product>>(
				responseStructure, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/product")
	public ResponseEntity<ResponseStructure<Product>> getProductsByname(@RequestParam("name") String name) {
		Product searchedProduct = productService.getProductByname(name);
		ResponseStructure<Product> responseStructure = new ResponseStructure<>();
		if (searchedProduct != null) {
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage(ApplicationConstants.PRODUCT_FOUND);
			responseStructure.setData(searchedProduct);

			ResponseEntity<ResponseStructure<Product>> responseEntity = new ResponseEntity<ResponseStructure<Product>>(
					responseStructure, HttpStatus.FOUND);
			return responseEntity;
		} else {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage(ApplicationConstants.PRODUCT_NOT_FOUND);
			responseStructure.setData(searchedProduct);

			ResponseEntity<ResponseStructure<Product>> responseEntity = new ResponseEntity<ResponseStructure<Product>>(
					responseStructure, HttpStatus.OK);
			return responseEntity;
		}
	}

}

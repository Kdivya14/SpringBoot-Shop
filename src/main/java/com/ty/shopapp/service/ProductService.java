package com.ty.shopapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.shopapp.constants.StockStatus;
import com.ty.shopapp.dao.ProductDao;
import com.ty.shopapp.dto.Product;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;

	public Product saveProduct(Product newProduct) {
		newProduct = updateProductStockStatus(newProduct);
		return productDao.addProduct(newProduct);
	}

	public Product getProduct(int id) {
		return productDao.getProduct(id);
	}
	
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	public Product updateProduct(int id, Product product) {
		Product productToBeUpdated = getProduct(id);
		if(productToBeUpdated != null) {
			productToBeUpdated.setProductId(id);
			productToBeUpdated.setName(product.getName());
			productToBeUpdated.setPrice(product.getPrice());
			productToBeUpdated.setQuantity(product.getQuantity());
			productToBeUpdated = updateProductStockStatus(productToBeUpdated);
			
			return productDao.updateProduct(productToBeUpdated);
		}
		return null;
	}

	private Product updateProductStockStatus(Product product) {
		int quantity = product.getQuantity();
		if (quantity <= 15) {
			//newProduct.setStockStatus("Dead stock");
			product.setStockStatus(StockStatus.DEAD_STOCK.value);
		} else if (quantity >= 15 && quantity <= 75) {
			//newProduct.setStockStatus("Moderate");
			product.setStockStatus(StockStatus.MODERATE.value);
		} else {
			//newProduct.setStockStatus("High");
			product.setStockStatus(StockStatus.HIGH.value);
		}
		return product;
	}

	public Product deleteProduct(int productId) {
		Product productToBeDeleted = getProduct(productId);
		if(productToBeDeleted != null) {
			productDao.deleteProduct(productToBeDeleted);
			return productToBeDeleted;
		}
		return null;
	}

	public Product getProductByname(String name) {
		return productDao.getProductByName(name);
	}

}

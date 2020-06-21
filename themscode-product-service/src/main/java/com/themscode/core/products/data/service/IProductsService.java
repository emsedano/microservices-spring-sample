package com.themscode.core.products.data.service;

import java.util.List;

import com.themscode.core.products.data.entity.Product;

public interface IProductsService {
	
	public List<Product> getAll();
	
	public Product findById(Long id);

}

package com.themscode.core.products.data.service;

import java.util.List;

import com.themscode.commons.models.entity.Product;

public interface IProductsService {
	
	public List<Product> getAll();
	
	public Product findById(Long id);
	
	public Product save(Product product);
	
	public void deleteById(Long  id);

}

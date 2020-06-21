package com.themscode.core.products.data.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.themscode.core.products.data.entity.Product;
import com.themscode.core.products.data.repository.ProductRepository;

@Service
public class ProductsService implements IProductsService {

	@Autowired private ProductRepository repository;

	@Transactional(readOnly = true)
	public List<Product> getAll() {
		return (List<Product>) this.repository.findAll();
	}

	@Transactional(readOnly = true)
	public Product findById(Long id) {
		return this.repository.findById(id).orElse(null);
	}

}

package com.themscode.core.products.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.themscode.core.products.data.entity.Product;
import com.themscode.core.products.data.service.IProductsService;

@RestController
public class ProductsController {
	
	@Autowired
	private Environment env;
	
	@Value("${server.port}")
	private int port;
	
	@Autowired 
	private IProductsService productsService;
	
	@GetMapping("/products")
	public List<Product> products() {
		System.out.println("*********** Reading prodcuts at: " + this.env.getProperty("server.port") + " / "  + this.port);
		return this.productsService.getAll();
	}
	
	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable Long id) {
		return this.productsService.findById(id);
	}

}

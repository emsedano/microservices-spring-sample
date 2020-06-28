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
	public List<Product> products() throws Exception {
		System.out.println("*********** Reading prodcuts at: " + this.env.getProperty("server.port") + " / "  + this.port);
		boolean circuiteBreaker = Math.random() > 0.95;
		System.out.println("circuiteBreaker value: " + circuiteBreaker);
		// to demonstrate hystrix and ribbon (failure tolerance
		/* if (circuiteBreaker) {
			throw new Exception("Circuit breaker error");
		}
		 
		try {
			Thread.sleep(2000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		return this.productsService.getAll();
	}
	
	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable Long id) {
		
		return this.productsService.findById(id);
	}

}

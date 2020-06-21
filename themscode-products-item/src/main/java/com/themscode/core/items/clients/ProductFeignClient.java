package com.themscode.core.items.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.themscode.core.items.model.Product;

@FeignClient(name = "product-service")
public interface ProductFeignClient {
	
	@GetMapping("/products") 
	List<Product> products();
	
	@GetMapping("/products/{id}")
	Product productItem(Long id); 

}

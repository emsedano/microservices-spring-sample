package com.themscode.core.items.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.themscode.commons.models.entity.Product;

@FeignClient(name = "product-service")
public interface ProductFeignClient {
	
	@GetMapping("/products") 
	List<Product> products();
	
	@GetMapping("/products/{id}")
	Product productItem(@PathVariable Long id);

	@PostMapping("/products")
	public Product save(@RequestBody Product product);
	
	@PutMapping("/products/{id}")
	public Product update(@PathVariable Long id, @RequestBody Product product);
	
	@DeleteMapping("/products/{id}")
	public void deleteById(@PathVariable Long  id);
	
}

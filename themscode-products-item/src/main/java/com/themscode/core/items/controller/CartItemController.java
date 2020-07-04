package com.themscode.core.items.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.themscode.core.items.model.CartItem;
import com.themscode.commons.models.entity.Product;
import com.themscode.core.items.service.ItemService;

@RefreshScope
@RestController
public class CartItemController {
	
	Logger log = LoggerFactory.getLogger(CartItemController.class);
	
	@Autowired
	@Qualifier("feignItemService")
	private ItemService itemService;
	
	@Value("${environment}")
	private String environment;
	
	@Value("${spring.application.name}")
	private String serviceName;
	
	@HystrixCommand(fallbackMethod = "fallbackCartItems")
	@GetMapping("/items")
	public List<CartItem> allCartItems() {
		return this.itemService.products();
	}
	
	public List<CartItem> fallbackCartItems() {
		Product product = new Product();
		product.setName("Fallback product");
		List<CartItem> response = new ArrayList<CartItem>();
		response.add(new CartItem(product , 0));
		return response;
	}
	
	
	@GetMapping("/items/{id}/quantity/{quantity}")
	public CartItem getCartItem(@PathVariable("id") Long id, @PathVariable("quantity") Integer quantity ) {
		return this.itemService.item(id, 1);
	}
	
	@PostMapping("/items")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Product createProductItem(@RequestBody Product product) {
		return this.itemService.save(product);
	}
	
	@PutMapping("/items/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Product updateProductItem(@RequestBody Product product, @PathVariable Long id) {
		return this.itemService.update(id, product);
	}
	
	@DeleteMapping("/items/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void updateProductItem(@PathVariable Long id) {
		this.itemService.delete(id);
	}
	
	
	@GetMapping("/config")
	public ResponseEntity<Map<String, String>> config() {
		Map<String, String> config = new HashMap<>();

		log.info(String.format("Service name: %s    environment: %s", serviceName, environment));
		config.put("environment", environment);
		config.put("serviceName", serviceName);
		
		return  new ResponseEntity<Map<String, String>>(config, HttpStatus.OK);
	}

}

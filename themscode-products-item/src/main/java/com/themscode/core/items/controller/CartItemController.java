package com.themscode.core.items.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.themscode.core.items.model.CartItem;
import com.themscode.core.items.model.Product;
import com.themscode.core.items.service.ItemService;

@RestController
public class CartItemController {
	
	@Autowired
	@Qualifier("feignItemService")
	private ItemService itemService;
	
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

}

package com.themscode.core.items.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.themscode.core.items.model.CartItem;
import com.themscode.core.items.model.Product;

@Service("restItemService")
public class RestItemService implements ItemService {
	
	@Autowired
	private RestTemplate restClient;

	public List<CartItem> products() {
		System.out.println("*********************** HELLO REST TMPL ************************");
		List<Product> products = Arrays.asList(this.restClient.getForObject("http://product-service/products", Product[].class));
		return products.stream().map(product -> new CartItem(product, 1)).collect(Collectors.toList());
	}

	public CartItem item(Long id, Integer quantity) {
		Map<String, String> params = new HashMap<>();
		params.put("id", id.toString());
		Product product = this.restClient.getForObject("http://product-service/products/{id}", Product.class, params);
		return new CartItem(product, quantity);
	}

}

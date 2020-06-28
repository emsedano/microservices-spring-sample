package com.themscode.core.items.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.themscode.core.items.clients.ProductFeignClient;
import com.themscode.core.items.model.CartItem;

@Service("feignItemService")
public class FeignItemService implements ItemService {

	@Autowired
	ProductFeignClient feignClient;
		
	public List<CartItem> products() {
		System.out.println("*********** FEIGN  CLIENT **********");
		return this.feignClient.products().stream().map(product -> new CartItem(product, 1)).collect(Collectors.toList());
	}

	public CartItem item(Long id, Integer quantity) {

		return new CartItem(this.feignClient.productItem(id), 1);
	}

}

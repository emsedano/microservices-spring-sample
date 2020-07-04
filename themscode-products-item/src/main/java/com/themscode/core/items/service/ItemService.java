package com.themscode.core.items.service;
import com.themscode.commons.models.entity.Product;

import java.util.List;

import com.themscode.core.items.model.CartItem;
import com.themscode.commons.models.entity.Product;

public interface ItemService {
	
	public List<CartItem> products();
	
	public CartItem item(Long id, Integer quantity);
	
	public Product save(Product product);
	
	public Product update(Long id, Product product);
	
	public void delete(Long id);

}

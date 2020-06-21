package com.themscode.core.items.service;

import java.util.List;

import com.themscode.core.items.model.CartItem;

public interface ItemService {
	
	public List<CartItem> products();
	
	public CartItem item(Long id, Integer quantity);

}

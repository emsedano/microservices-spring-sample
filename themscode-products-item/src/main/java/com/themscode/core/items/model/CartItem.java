package com.themscode.core.items.model;

import com.themscode.commons.models.entity.Product;

public class CartItem {

	private Integer quantity;
	private Product product;

	public CartItem() {
	}

	public CartItem(Product product, Integer quantity ) {
		this.quantity = quantity;
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double subTotal() {
		return this.product.getPrice() * this.quantity.doubleValue();
	}

}

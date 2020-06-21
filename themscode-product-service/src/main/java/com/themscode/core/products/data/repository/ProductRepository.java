package com.themscode.core.products.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.themscode.core.products.data.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}

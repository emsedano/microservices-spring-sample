package com.themscode.core.products.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.themscode.commons.models.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}

package com.example.co2Automatic.services;

import com.example.co2Automatic.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void save(Product product);

    List<Product> findAll();

    Optional<Product> findByProductName(String productName);

    Optional<Product> findById(long id);
}

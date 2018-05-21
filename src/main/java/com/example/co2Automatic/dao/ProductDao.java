package com.example.co2Automatic.dao;

import com.example.co2Automatic.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Long> {
}

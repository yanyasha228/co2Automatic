package com.example.co2Automatic.dao;

import com.example.co2Automatic.models.PhoneNumber;
import com.example.co2Automatic.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductDao extends JpaRepository<Product, Long> {

    Optional<Product> findProductByName(String name);
    Product findProductByNameLike(String nonFullProductName);
    Optional<Product> findById(long id);

}

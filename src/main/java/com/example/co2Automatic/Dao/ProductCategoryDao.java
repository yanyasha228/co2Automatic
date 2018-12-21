package com.example.co2Automatic.Dao;

import com.example.co2Automatic.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {

    Optional<ProductCategory> findByName(String productCategoryName);

    Optional<ProductCategory> findById(Integer id);

}

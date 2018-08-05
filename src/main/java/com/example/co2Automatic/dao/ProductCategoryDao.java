package com.example.co2Automatic.dao;

import com.example.co2Automatic.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {

}

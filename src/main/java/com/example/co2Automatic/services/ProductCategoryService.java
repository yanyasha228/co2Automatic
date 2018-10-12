package com.example.co2Automatic.services;


import com.example.co2Automatic.models.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryService {

    void save(ProductCategory productCategory);

    ProductCategory getOne(Integer id);

    void deleteById(Integer id);

    boolean existById(Integer id);

    List<ProductCategory> findAll();

    void saveAll(List<ProductCategory> productCategoryList);

    Optional<ProductCategory> findById(Integer id);

    Optional<ProductCategory> findByName(String productCategoryName);
}

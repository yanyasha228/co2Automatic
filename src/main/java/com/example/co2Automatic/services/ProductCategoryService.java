package com.example.co2Automatic.services;


import com.example.co2Automatic.models.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    void save(ProductCategory productCategory);

    ProductCategory getOne(Integer id);

    void deleteById(Integer id);

    boolean existById(Integer id);

    List<ProductCategory> findAll();

    void saveAll(List<ProductCategory> productCategoryList);
}

package com.example.co2Automatic.services;


import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleEntityUpdatingException;
import com.example.co2Automatic.models.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryService {

    ProductCategory save(ProductCategory productCategory);

    List<ProductCategory> save(List<ProductCategory> productCategories);

    ProductCategory update(ProductCategory productCategory) throws ImpossibleEntityUpdatingException;

    List<ProductCategory> update(List<ProductCategory> productCategories) throws ImpossibleEntityUpdatingException;





    void delete(Integer id);

    void delete(ProductCategory productCategory);

    void delete(List<ProductCategory> productCategories);

    boolean exist(Integer id);


    List<ProductCategory> findAll();


    Optional<ProductCategory> findById(Integer id);

    Optional<ProductCategory> findByName(String productCategoryName);
}

package com.example.co2Automatic.dao;

import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.models.ProductCategory;
import com.example.co2Automatic.models.ProductStock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface ProductDao extends JpaRepository<Product, Long> {

    Optional<Product> findProductByName(String name);

    Page<Product> findProductsByProductStock(ProductStock productStock ,
                                             Pageable pageable);

    Page<Product> findProductsByProductCategory(ProductCategory productCategory,Pageable pageable);

    Page<Product> findProductsByProductStockAndProductCategory(ProductStock productStock,
                                                               ProductCategory productCategory,
                                                               Pageable pageable);
    Product findProductByNameLike(String nonFullProductName);

    Optional<Product> findById(long id);

}

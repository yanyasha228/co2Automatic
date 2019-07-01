package com.example.co2Automatic.Dao;

import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.models.ProductCategory;
import com.example.co2Automatic.models.ModelEnums.ProductStock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface ProductDao extends JpaRepository<Product, Long> {

    Optional<Product> findProductByName(String name);

    Page<Product> findProductsByProductStock(ProductStock productStock,
                                             Pageable pageable);

    Page<Product> findProductsByProductCategory(ProductCategory productCategory, Pageable pageable);

    Page<Product> findProductsByProductStockAndProductCategory(ProductStock productStock,
                                                               ProductCategory productCategory,
                                                               Pageable pageable);

    Page<Product> findProductsByNameIgnoreCaseContaining(String name, Pageable pageable);

    Page<Product> findProductsByNameIgnoreCaseContainingAndProductStock(String name,
                                                        ProductStock productStock,
                                                        Pageable pageable);

    Page<Product> findProductsByNameIgnoreCaseContainingAndProductCategory(String name,
                                                           ProductCategory productCategory,
                                                           Pageable pageable);

    Page<Product> findProductsByNameIgnoreCaseContainingAndProductStockAndProductCategory(String name,
                                                                          ProductStock productStock,
                                                                          ProductCategory productCategory,
                                                                          Pageable pageable);


    Optional<Product> findById(long id);

    List<Product> findProductsByNameIgnoreCaseContaining(String nonFullName);

    @Query(nativeQuery = true)
    List<Product> findProductsByNameIgnoreCaseContainingAndAvailableIsTrue(String nonFullName);

//    Product findProductsByNameLike(String productName);

}

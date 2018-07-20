package com.example.co2Automatic.services;

import com.example.co2Automatic.ControllerHelpers.ProductListPageHelper;
import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.models.ProductCategory;
import com.example.co2Automatic.models.ProductStock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void save(Product product);

    //methods with filtering without search

    Page<Product> findAllWithPagination(ProductListPageHelper productListPageHelper,
                                        PageRequest pageRequest);

    Page<Product> findProductsByProductStockWithPagination(ProductListPageHelper productListPageHelper,
                                                           PageRequest pageRequest
    );

    Page<Product> findProductsByProductStockAndProductCategoryWithPagination(ProductListPageHelper productListPageHelper,
                                                                             PageRequest pageRequest);

    Page<Product> findProductsWithPagination(ProductListPageHelper productListPageHelper);

    Page<Product> findProductsByCategoryWithPagination(ProductListPageHelper productListPageHelper,
                                                       PageRequest pageRequest);
    // Search methods with filtering

    Page<Product> findProductsByNameLikeAndProductStockAndProductCategoryWithPagination(ProductListPageHelper productListPageHelper,
                                                                                        PageRequest pageRequest);

    Page<Product> findProductsByNameLikeWithPagination(ProductListPageHelper productListPageHelper, PageRequest pageRequest);

    Page<Product> findProductsByNameLikeAndProductStockWithPagination(ProductListPageHelper productListPageHelper,
                                                                      PageRequest pageRequest);

    Page<Product> findProductsByNameLikeAndProductCategoryWithPagination(ProductListPageHelper productListPageHelper,
                                                                         PageRequest pageRequest);

    List<Product> findAll();

    Optional<Product> findByProductName(String productName);

    Optional<Product> findById(long id);


}

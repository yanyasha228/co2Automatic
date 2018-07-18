package com.example.co2Automatic.services;

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
    Page<Product> findAllWithPagination(PageRequest pageRequest);

    Page<Product> findProductsByStockWithPagination(ProductStock productStock, PageRequest pageRequest);

    List<Product> findAll();

    Optional<Product> findByProductName(String productName);

    Optional<Product> findById(long id);

    Page<Product> findProductsWithFilteringAndPagination(int currentPageNumber, int pageSize, ProductStock productsStockSorting, ProductCategory productCategory);
}

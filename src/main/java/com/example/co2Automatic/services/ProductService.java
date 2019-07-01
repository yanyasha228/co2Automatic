package com.example.co2Automatic.services;

import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleEntityUpdatingException;
import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.models.ProductCategory;
import com.example.co2Automatic.models.ModelEnums.ProductStock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);


    Page<Product> findAllWithPagination(Pageable pageable);

    Page<Product> findProductsByProductStockWithPagination(ProductStock productsStockSorting, Pageable pageable);

    Page<Product> findProductsByProductStockAndProductCategoryWithPagination(ProductStock productStock,
                                                                             ProductCategory productCategory,
                                                                             Pageable pageable);

    Page<Product> findProductsByCategoryWithPagination(ProductCategory productCategory, Pageable pageable);

    //////////////////////////////////////////////////////////

//Filtering methods with search//////////////////////////////////
    Page<Product> findProductsByNameLikeAndProductStockAndProductCategoryWithPagination(String productNameSearchInput,
                                                                                        ProductStock productStock,
                                                                                        ProductCategory productCategory,
                                                                                        Pageable pageable);

    Page<Product> findProductsByNameLikeWithPagination(String productNameSearchInput,
                                                       Pageable pageable);

    Page<Product> findProductsByNameLikeAndProductStockWithPagination(String productNameSearchInput,
                                                                      ProductStock productStock,
                                                                      Pageable pageable);

    Page<Product> findProductsByNameLikeAndProductCategoryWithPagination(String productNameSearchInput,
                                                                         ProductCategory productCategory,
                                                                         Pageable pageable);

    List<Product> findAll();

    Optional<Product> findByProductName(String productName);

    Optional<Product> findById(Long id);

    List<Product> findProductByNonFullProductName(String nonFullProductName);

    List<Product> findProductByNonFullProductNameRegardlessOfTheWordsOrder(String nonFullProductName);

    List<Product> save(List<Product> newProductList);

    void delete(Long id);

    void delete(List<Product> productList);

    Product update(Product product) throws ImpossibleEntityUpdatingException;

    List<Product> update(List<Product> productList) throws ImpossibleEntityUpdatingException;

    Page<Product> findProductsWithPagination(Pageable pageable,
                                             ProductStock productStock,
                                             ProductCategory productCategory,
                                             String productNameSearchInput);
}

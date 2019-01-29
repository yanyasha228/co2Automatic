package com.example.co2Automatic.services;

import com.example.co2Automatic.models.MoneyCurrency;
import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.models.ProductCategory;
import com.example.co2Automatic.models.ProductStock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void save(Product product);


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

    Optional<Product> findById(long id);

    List<Product> findProductByNonFullProductName(String nonFullProductName);

    List<Product> findProductByNonFullProductNameRegardlessOfTheWordsOrder(String nonFullProductName);

    void saveAll(List<Product> newProductList);

    void deleteById(Long id);

    Product saveAndReturnEntity(Product product);

    void updateProduct(Integer productId, String inputProductName, String inputProductVendor, String inputProductCountryOfOrigin, ProductStock inputProductStock, String inputProductCategory, Double inputProductPrice, Double inputProductWholeSalePrice, MoneyCurrency inputProductMoneyCurrency, String inputProductDescription, String[] inputProductParamName, String[] inputProductParamValue);

    Page<Product> findProductsWithPagination(Pageable pageable,
                                             ProductStock productStock,
                                             ProductCategory productCategory,
                                             String productNameSearchInput);
}

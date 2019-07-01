package com.example.co2Automatic.services;

import com.example.co2Automatic.Dao.ProductDao;
import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleEntityUpdatingException;
import com.example.co2Automatic.models.*;
import com.example.co2Automatic.models.ModelEnums.MoneyCurrency;
import com.example.co2Automatic.models.HelpModels.ProductParam;
import com.example.co2Automatic.models.ModelEnums.ProductStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Override
    public Page<Product> findAllWithPagination(Pageable pageable) {

        return productDao.findAll(pageable);
    }
    //////////////////////////////////////////////////////////
//Filtering methods without search

    @Override
    public Page<Product> findProductsByProductStockWithPagination(ProductStock productsStockSorting, Pageable pageable) {
        return productDao.findProductsByProductStock(productsStockSorting, pageable);
    }

    @Override
    public Page<Product> findProductsByProductStockAndProductCategoryWithPagination(ProductStock productStock,
                                                                                    ProductCategory productCategory,
                                                                                    Pageable pageable) {
        return productDao.findProductsByProductStockAndProductCategory(productStock,
                productCategory,
                pageable);
    }

    @Override
    public Page<Product> findProductsByCategoryWithPagination(ProductCategory productCategory, Pageable pageable) {
        return productDao.findProductsByProductCategory(productCategory, pageable);
    }

    //////////////////////////////////////////////////////////
//Filtering methods with search//////////////////////////////////
    @Override
    public Page<Product> findProductsByNameLikeAndProductStockAndProductCategoryWithPagination(String productNameSearchInput,
                                                                                               ProductStock productStock,
                                                                                               ProductCategory productCategory,
                                                                                               Pageable pageable) {

        return productDao.findProductsByNameIgnoreCaseContainingAndProductStockAndProductCategory(productNameSearchInput,
                productStock,
                productCategory,
                pageable);
    }

    @Override
    public Page<Product> findProductsByNameLikeWithPagination(String productNameSearchInput,
                                                              Pageable pageable) {

        return productDao.findProductsByNameIgnoreCaseContaining(productNameSearchInput,
                pageable);

    }

    @Override
    public Page<Product> findProductsByNameLikeAndProductStockWithPagination(String productNameSearchInput,
                                                                             ProductStock productStock,
                                                                             Pageable pageable) {

        return productDao.findProductsByNameIgnoreCaseContainingAndProductStock(productNameSearchInput,
                productStock,
                pageable);
    }

    @Override
    public Page<Product> findProductsByNameLikeAndProductCategoryWithPagination(String productNameSearchInput,
                                                                                ProductCategory productCategory,
                                                                                Pageable pageable) {

        return productDao.findProductsByNameIgnoreCaseContainingAndProductCategory(productNameSearchInput,
                productCategory,
                pageable);
    }
////////////////////////////////////////////////////////

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }


    @Override
    public Optional<Product> findByProductName(String productName) {

        return productDao.findProductByName(productName);

    }



    @Override
    public Optional<Product> findById(Long id) {
        return productDao.findById(id);
    }

    @Override
    public List<Product> findProductByNonFullProductName(String nonFullProductName) {
        return productDao.findProductsByNameIgnoreCaseContaining(nonFullProductName);
    }

    @Override
    public List<Product> findProductByNonFullProductNameRegardlessOfTheWordsOrder(String nonFullProductName) {

        String[] searchingWords = nonFullProductName.split("\\s");

        List<Product> firstWordSearchFromDb;

        List<Product> productsThatMatch = new ArrayList<>();

        if (searchingWords.length != 0 && !searchingWords[0].equalsIgnoreCase("")) {
            firstWordSearchFromDb = productDao.findProductsByNameIgnoreCaseContaining(searchingWords[0]);
        } else return productsThatMatch;


        if (firstWordSearchFromDb.size() != 0) {
            out:
            for (Product prodForSearch : firstWordSearchFromDb) {
                for (int i = 1; i < searchingWords.length; i++) {
                    if (!prodForSearch.getName().toLowerCase().contains(searchingWords[i])) continue out;
                }
                productsThatMatch.add(prodForSearch);
            }
        }


        return productsThatMatch;
    }

    @Override
    public List<Product> save(List<Product> newProductList) {

        return productDao.saveAll(newProductList);

    }

    @Override
    public void delete(Long id) {
        productDao.deleteById(id);
    }

    @Override
    public void delete(List<Product> productList) {
        productDao.deleteAll(productList);
    }

    @Override
    public Product save(Product product) {
        return productDao.save(product);
    }

    @Override
    public Product update(Product product) throws ImpossibleEntityUpdatingException {
        if (product.getId() == 0)
            throw new ImpossibleEntityUpdatingException("Attempt to update entity without ID!!!");

            return productDao.save(product);

    }

    @Override
    public List<Product> update(List<Product> productList) throws ImpossibleEntityUpdatingException {
        for ( Product oneOf : productList ) {
            if (oneOf.getId() <= 0)
                throw new ImpossibleEntityUpdatingException("Attempt to update entity without ID!!!");
        }
        return productDao.saveAll(productList);
    }

    public void updateProduct(Integer productId,
                              String inputProductName,
                              String inputProductVendor,
                              String inputProductCountryOfOrigin,
                              ProductStock inputProductStock,
                              String inputProductCategory,
                              Double inputProductPrice,
                              Double inputProductWholeSalePrice,
                              MoneyCurrency inputProductMoneyCurrency,
                              String inputProductDescription,
                              String[] inputProductParamName,
                              String[] inputProductParamValue) {

        Product productForUpdating = productDao.findById(productId).orElse(new Product());

        productForUpdating.setName(inputProductName);
        productForUpdating.setVendor(inputProductVendor);
        productForUpdating.setCountryOfOrigin(inputProductCountryOfOrigin);
        productForUpdating.setProductStock(inputProductStock);
        if (productCategoryService.findByName(inputProductCategory).isPresent())
            productForUpdating.setProductCategory(productCategoryService.findByName(inputProductCategory).get());
        productForUpdating.setPrice(inputProductPrice);
        productForUpdating.setWholeSalePrice(inputProductWholeSalePrice);
        productForUpdating.setDescription(inputProductDescription);


        List<ProductParam> newProdParams = new ArrayList<>();
        for (int i = 0; i < inputProductParamName.length; i++) {
            if (!inputProductParamName[i].replaceAll(" ", "").isEmpty()
                    || !inputProductParamValue[i].replaceAll(" ", "").isEmpty()) {
                newProdParams.add(new ProductParam(inputProductParamName[i], inputProductParamValue[i]));
            }
        }
        productForUpdating.setParams(newProdParams);
        productDao.save(productForUpdating);
    }

    @Override
    public Page<Product> findProductsWithPagination(Pageable pageable,
                                                    ProductStock productStock,
                                                    ProductCategory productCategory,
                                                    String productNameSearchInput) {

        if (productNameSearchInput.replaceAll(" ", "").isEmpty()) {

            if (productCategory == null && productStock != null) {

                return findProductsByProductStockWithPagination(productStock, pageable);
            }

            if (productCategory != null && productStock == null) {

                return findProductsByCategoryWithPagination(productCategory, pageable);
            }
            if (productCategory != null && productStock != null) {
                return findProductsByProductStockAndProductCategoryWithPagination(productStock,
                        productCategory,
                        pageable);
            }
        } else {

            if (productCategory == null && productStock != null) {

                return findProductsByNameLikeAndProductStockWithPagination(productNameSearchInput, productStock, pageable);
            }

            if (productCategory != null && productStock == null) {

                return findProductsByNameLikeAndProductCategoryWithPagination(productNameSearchInput,
                        productCategory,
                        pageable);
            }
            if (productCategory != null && productStock != null) {
                return findProductsByNameLikeAndProductStockAndProductCategoryWithPagination(productNameSearchInput,
                        productStock,
                        productCategory,
                        pageable);
            }

            return findProductsByNameLikeWithPagination(productNameSearchInput, pageable);
        }

        return findAllWithPagination(pageable);
    }

}


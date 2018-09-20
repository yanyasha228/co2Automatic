package com.example.co2Automatic.services;

import com.example.co2Automatic.ControllerHelpers.ProductListPageHelper;
import com.example.co2Automatic.dao.ProductDao;
import com.example.co2Automatic.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public Page<Product> findAllWithPagination(ProductListPageHelper productListPageHelper, PageRequest pageRequest) {
        Page<Product> resultPage = productDao.findAll(pageRequest);
        productListPageHelper.setTotalPagesAmount(resultPage.getTotalPages());
        return resultPage;
    }
    //////////////////////////////////////////////////////////
//Filtering methods without search

    @Override
    public Page<Product> findProductsByProductStockWithPagination(ProductListPageHelper productListPageHelper, PageRequest pageRequest) {
        Page<Product> resultPage = productDao.findProductsByProductStock(productListPageHelper.getProductsStockSorting(), pageRequest);
        productListPageHelper.setTotalPagesAmount(resultPage.getTotalPages());
        return resultPage;
    }

    @Override
    public Page<Product> findProductsByProductStockAndProductCategoryWithPagination(ProductListPageHelper productListPageHelper, PageRequest pageRequest) {
        Page<Product> resultPage = productDao.findProductsByProductStockAndProductCategory(productListPageHelper.getProductsStockSorting(),
                productListPageHelper.getProductsCategorySorting(),
                pageRequest);
        productListPageHelper.setTotalPagesAmount(resultPage.getTotalPages());
        return resultPage;
    }

    @Override
    public Page<Product> findProductsByCategoryWithPagination(ProductListPageHelper productListPageHelper, PageRequest pageRequest) {
        Page<Product> resultPage = productDao.findProductsByProductCategory(productListPageHelper.getProductsCategorySorting(), pageRequest);
        productListPageHelper.setTotalPagesAmount(resultPage.getTotalPages());
        return resultPage;
    }

    //////////////////////////////////////////////////////////
//Filtering methods with search//////////////////////////////////
    @Override
    public Page<Product> findProductsByNameLikeAndProductStockAndProductCategoryWithPagination(ProductListPageHelper productListPageHelper, PageRequest pageRequest) {
        Page<Product> resultPage = productDao.findProductsByNameIgnoreCaseContainingAndProductStockAndProductCategory(productListPageHelper.getProductListSearchParam(),
                productListPageHelper.getProductsStockSorting(),
                productListPageHelper.getProductsCategorySorting(),
                pageRequest);
        productListPageHelper.setTotalPagesAmount(resultPage.getTotalPages());
        return resultPage;
    }

    @Override
    public Page<Product> findProductsByNameLikeWithPagination(ProductListPageHelper productListPageHelper, PageRequest pageRequest) {
        Page<Product> resultPage = productDao.findProductsByNameIgnoreCaseContaining(productListPageHelper.getProductListSearchParam(),
                pageRequest);
        productListPageHelper.setTotalPagesAmount(resultPage.getTotalPages());
        return resultPage;

    }

    @Override
    public Page<Product> findProductsByNameLikeAndProductStockWithPagination(ProductListPageHelper productListPageHelper, PageRequest pageRequest) {
        Page<Product> resultPage = productDao.findProductsByNameIgnoreCaseContainingAndProductStock(productListPageHelper.getProductListSearchParam(),
                productListPageHelper.getProductsStockSorting(),
                pageRequest);
        productListPageHelper.setTotalPagesAmount(resultPage.getTotalPages());
        return resultPage;
    }

    @Override
    public Page<Product> findProductsByNameLikeAndProductCategoryWithPagination(ProductListPageHelper productListPageHelper, PageRequest pageRequest) {
        Page<Product> resultPage = productDao.findProductsByNameIgnoreCaseContainingAndProductCategory(productListPageHelper.getProductListSearchParam(),
                productListPageHelper.getProductsCategorySorting(),
                pageRequest);
        productListPageHelper.setTotalPagesAmount(resultPage.getTotalPages());
        return resultPage;
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
    public Optional<Product> findById(long id) {
        return productDao.findById(id);
    }

    @Override
    public void saveAll(List<Product> newProductList) {

        productDao.saveAll(newProductList);

    }

    @Override
    public void deleteById(Long id) {
        productDao.deleteById(id);
    }

    @Override
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


        Map<String, String> newProdParams = new HashMap<>();
        for (int i = 0; i < inputProductParamName.length; i++) {
            if(!inputProductParamName[i].replaceAll(" ","").isEmpty()
                    || !inputProductParamValue[i].replaceAll(" ","").isEmpty()){
                newProdParams.put(inputProductParamName[i], inputProductParamValue[i]);
            }
        }
        productForUpdating.setParams(newProdParams);
        productDao.save(productForUpdating);
    }


    @Override
    public Page<Product> findProductsWithPagination(ProductListPageHelper productListPageHelper) {

        if (productListPageHelper.getProductListSearchParam() == null) {

            if (productListPageHelper.getProductsCategorySorting().getId() == 0 && productListPageHelper.getProductsStockSorting() != null) {

                return findProductsByProductStockWithPagination(productListPageHelper, PageRequest.of(productListPageHelper.getCurrentPageNumber(),
                        productListPageHelper.getPageSize(),
                        Sort.Direction.ASC,
                        "id"));
            }

            if (productListPageHelper.getProductsCategorySorting().getId() != 0 && productListPageHelper.getProductsStockSorting() == null) {

                return findProductsByCategoryWithPagination(productListPageHelper, PageRequest.of(productListPageHelper.getCurrentPageNumber(),
                        productListPageHelper.getPageSize(),
                        Sort.Direction.ASC,
                        "id"));
            }
            if (productListPageHelper.getProductsCategorySorting().getId() != 0 && productListPageHelper.getProductsStockSorting() != null) {
                return findProductsByProductStockAndProductCategoryWithPagination(productListPageHelper,
                        PageRequest.of(productListPageHelper.getCurrentPageNumber(),
                                productListPageHelper.getPageSize(),
                                Sort.Direction.ASC,
                                "id"));
            }
        } else {

            if (productListPageHelper.getProductsCategorySorting().getId() == 0 && productListPageHelper.getProductsStockSorting() != null) {

                return findProductsByNameLikeAndProductStockWithPagination(productListPageHelper, PageRequest.of(productListPageHelper.getCurrentPageNumber(),
                        productListPageHelper.getPageSize(),
                        Sort.Direction.ASC,
                        "id"));
            }

            if (productListPageHelper.getProductsCategorySorting().getId() != 0 && productListPageHelper.getProductsStockSorting() == null) {

                return findProductsByNameLikeAndProductCategoryWithPagination(productListPageHelper, PageRequest.of(productListPageHelper.getCurrentPageNumber(),
                        productListPageHelper.getPageSize(),
                        Sort.Direction.ASC,
                        "id"));
            }
            if (productListPageHelper.getProductsCategorySorting().getId() != 0 && productListPageHelper.getProductsStockSorting() != null) {
                return findProductsByNameLikeAndProductStockAndProductCategoryWithPagination(productListPageHelper,
                        PageRequest.of(productListPageHelper.getCurrentPageNumber(),
                                productListPageHelper.getPageSize(),
                                Sort.Direction.ASC,
                                "id"));
            }

            return findProductsByNameLikeWithPagination(productListPageHelper, PageRequest.of(productListPageHelper.getCurrentPageNumber(),
                    productListPageHelper.getPageSize(),
                    Sort.Direction.ASC,
                    "id"));
        }

        return findAllWithPagination(productListPageHelper, PageRequest.of(productListPageHelper.getCurrentPageNumber(),
                productListPageHelper.getPageSize(),
                Sort.Direction.ASC,
                "id"));
    }

}

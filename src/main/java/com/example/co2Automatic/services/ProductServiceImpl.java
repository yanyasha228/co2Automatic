package com.example.co2Automatic.services;

import com.example.co2Automatic.ControllerHelpers.ProductListPageHelper;
import com.example.co2Automatic.dao.ProductDao;
import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.models.ProductCategory;
import com.example.co2Automatic.models.ProductStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

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
    public Page<Product> findProductsWithFilteringAndPagination(ProductListPageHelper productListPageHelper) {


        if (productListPageHelper.getProductsCategorySorting() == null && productListPageHelper.getProductsStockSorting() != null) {

            return findProductsByProductStockWithPagination(productListPageHelper, PageRequest.of(productListPageHelper.getCurrentPageNumber(),
                    productListPageHelper.getPageSize(),
                    Sort.Direction.ASC,
                    "id"));
        }

        if (productListPageHelper.getProductsCategorySorting() != null && productListPageHelper.getProductsStockSorting() == null) {

            return findProductsByCategoryWithPagination(productListPageHelper, PageRequest.of(productListPageHelper.getCurrentPageNumber(),
                    productListPageHelper.getPageSize(),
                    Sort.Direction.ASC,
                    "id"));
        }
        if (productListPageHelper.getProductsCategorySorting() != null && productListPageHelper.getProductsStockSorting() != null) {
            return findProductsByProductStockAndProductCategoryWithPagination(productListPageHelper,
                    PageRequest.of(productListPageHelper.getCurrentPageNumber(),
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

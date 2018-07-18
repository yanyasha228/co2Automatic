package com.example.co2Automatic.services;

import com.example.co2Automatic.dao.ProductDao;
import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.models.ProductCategory;
import com.example.co2Automatic.models.ProductStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<Product> findAllWithPagination(PageRequest pageRequest) {
        return productDao.findAll(pageRequest);
    }

    @Override
    public Page<Product> findProductsByStockWithPagination(ProductStock productStock, PageRequest pageRequest) {
        return productDao.findProductsByProductStock(productStock, pageRequest);
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
    public Page<Product> findProductsWithFilteringAndPagination(int currentPageNumber, int pageSize, ProductStock productsStockSorting, ProductCategory productCategory) {

        return null;
    }

}

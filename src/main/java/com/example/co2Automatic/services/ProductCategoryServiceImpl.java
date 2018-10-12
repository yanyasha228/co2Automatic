package com.example.co2Automatic.services;

import com.example.co2Automatic.dao.ProductCategoryDao;
import com.example.co2Automatic.models.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    ProductCategoryDao productCategoryDao;

    @Override
    public void save(ProductCategory productCategory) {
        productCategoryDao.save(productCategory);
    }

    @Override
    public ProductCategory getOne(Integer id) {
        return productCategoryDao.getOne(id);
    }

    @Override
    public void deleteById(Integer id) {
        productCategoryDao.deleteById(id);
    }

    @Override
    public boolean existById(Integer id) {
        return productCategoryDao.existsById(id);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    @Override
    public void saveAll(List<ProductCategory> productCategoryList) {
        productCategoryDao.saveAll(productCategoryList);
    }

    @Override
    public Optional<ProductCategory> findById(Integer id) {
        return productCategoryDao.findById(id);
    }

    @Override
    public Optional<ProductCategory> findByName(String productCategoryName) {
        return productCategoryDao.findByName(productCategoryName);
    }
}

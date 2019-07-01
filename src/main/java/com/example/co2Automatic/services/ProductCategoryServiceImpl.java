package com.example.co2Automatic.services;

import com.example.co2Automatic.Dao.ProductCategoryDao;
import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleEntityUpdatingException;
import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.models.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public ProductCategory update(ProductCategory productCategory) throws ImpossibleEntityUpdatingException {
        if (productCategory.getId() == 0)
            throw new ImpossibleEntityUpdatingException("Attempt to update entity without ID!!!");

            return productCategoryDao.save(productCategory);

    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDao.save(productCategory);
    }

    @Override
    public List<ProductCategory> update(List<ProductCategory> productCategories) throws ImpossibleEntityUpdatingException {
        for (ProductCategory oneOf : productCategories) {
            if (oneOf.getId() <= 0)
                throw new ImpossibleEntityUpdatingException("Attempt to update entity without ID!!!");
        }
        return productCategoryDao.saveAll(productCategories);
    }

    @Override
    public void delete(Integer id) {
        productCategoryDao.deleteById(id);
    }

    @Override
    public void delete(ProductCategory productCategory) {

    }

    @Override
    public void delete(List<ProductCategory> productCategories) {

    }

    @Override
    public boolean exist(Integer id) {
        return productCategoryDao.existsById(id);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    @Override
    public List<ProductCategory> save(List<ProductCategory> productCategoryList) {
        return productCategoryDao.saveAll(productCategoryList);
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

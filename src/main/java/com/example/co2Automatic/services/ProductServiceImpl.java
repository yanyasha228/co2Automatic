package com.example.co2Automatic.services;

import com.example.co2Automatic.dao.ProductDao;
import com.example.co2Automatic.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
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

}

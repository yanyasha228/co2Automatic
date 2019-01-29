package com.example.co2Automatic.services;

import com.example.co2Automatic.Dao.ProductCategoryDao;
import com.example.co2Automatic.Dao.ProductDao;
import com.example.co2Automatic.models.Client;
import com.example.co2Automatic.models.ClientStatus;
import com.example.co2Automatic.models.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductDao productDao;

    @MockBean
    private ProductCategoryService productCategoryService;

    @MockBean
    private ProductCategoryDao productCategoryDao;


    @Test
    public void addProduct(){

    }
}
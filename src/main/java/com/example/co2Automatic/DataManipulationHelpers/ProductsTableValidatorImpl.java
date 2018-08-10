package com.example.co2Automatic.DataManipulationHelpers;

import com.example.co2Automatic.SystemComponents.AdminSettings;
import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.models.ProductCategory;
import com.example.co2Automatic.services.ProductCategoryService;
import com.example.co2Automatic.services.ProductService;
import com.example.co2Automatic.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class ProductsTableValidatorImpl implements ProductsTableValidator {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    AdminSettings adminSettings;

    public ProductsTableValidatorImpl() {
    }

    @Override
    public void validateCategoriesTable(List<ProductCategory> productCategoryList){
        productCategoryService.saveAll(productCategoryList);
    }

    @Override
    public void validateProductsTable(List<Product> newProductsList) {

        List<Product> productListForValidation = productService.findAll();

        if (productListForValidation != null && newProductsList != null) {
            for (Product newProd : newProductsList) {
                for (Product oldProd : productListForValidation) {
                    if(newProd.getProductUrlFromExternalResource().equalsIgnoreCase(oldProd.getProductUrlFromExternalResource())){
                        mergeProductWithExternalResourceProduct(oldProd,newProd);
                    }else productListForValidation.add(newProd);
                }
            }
            productService.saveAll(productListForValidation);
        }
    }


    private void mergeProductWithExternalResourceProduct(Product oldProduct, Product newProduct) {

        oldProduct.setDescription(newProduct.getDescription());
        oldProduct.setName(newProduct.getName());
        oldProduct.setPrice(newProduct.getPrice());
        oldProduct.setProductCategory(newProduct.getProductCategory());
        oldProduct.setCountryOfOrigin(newProduct.getCountryOfOrigin());
        oldProduct.setVendor(newProduct.getVendor());
        oldProduct.setImageUrls(newProduct.getImageUrls());
        oldProduct.setParams(newProduct.getParams());

    }

}
package com.example.co2Automatic.HelpUtils.DataManipulationHelpers;

import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.models.ProductCategory;

import java.util.List;

public interface ProductsTableValidator {
    void validateCategoriesTable(List<ProductCategory> productCategoryList);
    void validateProductsTable(List<Product> products);
    void validateProductsAndProductCategoriesTableByXmlString(String xmlString);
 }

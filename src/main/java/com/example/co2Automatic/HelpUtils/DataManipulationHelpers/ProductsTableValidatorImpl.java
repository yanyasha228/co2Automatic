package com.example.co2Automatic.HelpUtils.DataManipulationHelpers;

import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.models.ProductCategory;
import com.example.co2Automatic.services.AppSettingsModelService;
import com.example.co2Automatic.services.ProductCategoryService;
import com.example.co2Automatic.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductsTableValidatorImpl implements ProductsTableValidator {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private AppSettingsModelService appSettingsModelService;

    public ProductsTableValidatorImpl() {
    }

    @Override
    public void validateCategoriesTable(List<ProductCategory> productCategoryList) {
        productCategoryService.save(productCategoryList);
    }

    @Override
    public void validateProductsTable(List<Product> newProductsList) {

        List<Product> productListForValidation = productService.findAll();

        if (productListForValidation.size() != 0 && newProductsList != null) {
            for (Product newProd : newProductsList) {

//                for (int i = 0; i < productListForValidation.size(); i++) {
//                    if (newProd.getProductUrlFromExternalResource().equalsIgnoreCase(productListForValidation.get(i).getProductUrlFromExternalResource())) {
//                        mergeProductWithExternalResourceProduct(productListForValidation.get(i), newProd);
//                        prodExist = true;
//                    }
//                }
                Optional<Product> matchingProduct = productListForValidation.
                        stream().
                        filter(product -> product.getProductUrlFromExternalResource().equalsIgnoreCase(newProd.getProductUrlFromExternalResource())).
                        findFirst();

                if (matchingProduct.orElse(null) != null) {
                    mergeProductWithExternalResourceProduct(matchingProduct.get(), newProd);

                } else productListForValidation.add(newProd);
            }
        }

        if (productListForValidation.size() == 0 && newProductsList != null) {
            for (Product product : newProductsList) {
                product.setProductCategory(productCategoryService.findById(product.getCategoryXmlId()).orElse(null));
                productListForValidation.add(product);
            }
        }
        productService.save(productListForValidation);

    }


    @Override
    public void validateProductsAndProductCategoriesTableByXmlString(String xmlString) {
        validateCategoriesTable(new ProductsXmlUnmarshaller(xmlString).buildXml().getCategoriesFromXml());
        validateProductsTable(new ProductsXmlUnmarshaller(xmlString).buildXml().getProductListFromXml());
    }


    private void mergeProductWithExternalResourceProduct(Product oldProduct, Product newProduct) {

        oldProduct.setDescription(newProduct.getDescription());
        oldProduct.setName(newProduct.getName());
        oldProduct.setPrice(newProduct.getPrice());
        oldProduct.setProductCategory(newProduct.getProductCategory());
        oldProduct.setCountryOfOrigin(newProduct.getCountryOfOrigin());
        oldProduct.setVendor(newProduct.getVendor());
        oldProduct.setImages(newProduct.getImages());
        oldProduct.setParams(newProduct.getParams());
        oldProduct.setCurrency(newProduct.getCurrency());
        oldProduct.setProductCategory(productCategoryService.findById(newProduct.getCategoryXmlId()).orElse(null));

    }

}
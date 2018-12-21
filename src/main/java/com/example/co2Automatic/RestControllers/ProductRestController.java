package com.example.co2Automatic.RestControllers;

import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restApi/products")
public class ProductRestController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/getProductsByNonFullName", method = RequestMethod.GET)
    public List<Product> getProductsByNonFullName(@RequestParam(value = "search_S") String nonFullNameString) {
        return productService.findProductByNonFullProductNameRegardlessOfTheWordsOrder(nonFullNameString);

    }

    @RequestMapping(value = "/getProductByName", method = RequestMethod.GET)
    public Product getProductByName(@RequestParam(value = "search_S") String productName) {

        return productService.findByProductName(productName).orElse(null);
    }


    @RequestMapping(value = "/getProductById", method = RequestMethod.GET)
    public Product getProductById(@RequestParam(value = "search_Id") Long id) {

        return productService.findById(id).orElse(null);
    }
}

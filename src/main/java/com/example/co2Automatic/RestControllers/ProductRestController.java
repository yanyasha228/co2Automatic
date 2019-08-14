package com.example.co2Automatic.RestControllers;

import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restApi/products")
public class ProductRestController {

    @Autowired
    ProductService productService;

    @GetMapping("all")
    public List<Product> productList(){
        return productService.findAll();
    }

    @GetMapping(params = "nonFullProductName")
    public List<Product> getProductsByNonFullName(@RequestParam(value = "nonFullProductName") String nonFullNameString) {
        return productService.findProductByNonFullProductNameRegardlessOfTheWordsOrder(nonFullNameString);

    }

    @GetMapping(params = "productName")
    public Product getProductByName(@RequestParam(value = "productName") String productName) {

        return productService.findByProductName(productName).orElse(null);
    }


    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable Long id) {

        return productService.findById(id).orElse(null);
    }

    @DeleteMapping(value = "/{id}")
    public Product deleteProduct(@PathVariable Long id) {
        Optional<Product> productForDel = productService.findById(id);
        if (productForDel.isPresent()) {
            productService.delete(id);
        }

        return productForDel.orElse(null);
    }

}

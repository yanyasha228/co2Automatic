package com.example.co2Automatic.Controllers;

import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.services.ProductCategoryService;
import com.example.co2Automatic.services.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products/create")
public class ProductsCreateController {

    private static final Logger logger = Logger.getLogger(ProductsCreateController.class);

    @Autowired
    ProductService productService;

    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping
    public String createProduct(Model model) {

        Product productToCreate = new Product();

        model.addAttribute("product", productToCreate);
        model.addAttribute("productCategories", productCategoryService.findAll());

        return "editProduct";
    }

}

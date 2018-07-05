package com.example.co2Automatic.controllers;

import com.example.co2Automatic.models.Order;
import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping
    public String productsList(Model model, @RequestParam(defaultValue = "0") int productsPageNumber , @RequestParam(defaultValue = "5") int productsPageSize) {
        if((productsPageSize > 100) || (productsPageSize <= 0)) productsPageSize=5;

        model.addAttribute("productsList", productService.findAllWithPagination(PageRequest.of(productsPageNumber, productsPageSize , Sort.Direction.DESC, "creationDate")));

        model.addAttribute("currentProductsPageSize", productsPageSize);

        model.addAttribute("currentPage",productsPageNumber);

        return "products";
    }
}

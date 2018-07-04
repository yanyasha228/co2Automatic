package com.example.co2Automatic.controllers;

import com.example.co2Automatic.models.Order;
import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping
    public String mainPage(Model model) {

        model.addAttribute("productsList", productService.findAll());

        return "products";
    }
}

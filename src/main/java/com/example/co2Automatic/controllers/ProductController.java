package com.example.co2Automatic.controllers;

import com.example.co2Automatic.ControllerHelpers.ProductListPageHelper;
import com.example.co2Automatic.models.Order;
import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("products")
public class ProductController {

     ProductListPageHelper productListPageHelper = new ProductListPageHelper(0,5);
    @Autowired
    private ProductService productService;


    @RequestMapping
    public String productsList(Model model, @RequestParam(required = false) Integer productsPageNumber , @RequestParam(required = false) Integer productsPageSize ) {

        if(productsPageNumber!=null && productsPageNumber >= 0)productListPageHelper.setCurrentPageNumber(productsPageNumber);

        if(productsPageSize!=null)productListPageHelper.setPageSize(productsPageSize);

        model.addAttribute("productsList", productService.findAllWithPagination(PageRequest.of(productListPageHelper.getCurrentPageNumber(), productListPageHelper.getPageSize(), Sort.Direction.ASC, "creationDate")));

        model.addAttribute("productListPageHelper", productListPageHelper);
        return "products";
    }

}

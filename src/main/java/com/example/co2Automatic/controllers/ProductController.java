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
@SessionAttributes("productListPageHelper")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ModelAttribute("productListPageHelper")
    public ProductListPageHelper createProductListPageHelper() {
        return new ProductListPageHelper(0, 5);
    }

    @RequestMapping
    public String productsList(Model model, @ModelAttribute ProductListPageHelper productListPageHelper) {
        model.addAttribute("productsList", productService.findAllWithPagination(PageRequest.of(productListPageHelper.getCurrentPageNumber(), productListPageHelper.getPageSize(), Sort.Direction.ASC, "creationDate")));

        return "products";
    }

    @RequestMapping
    public String setProductListPageNumber(Model model, @ModelAttribute ProductListPageHelper productListPageHelper, @RequestParam int productsPageNumber) {
        productListPageHelper.setCurrentPageNumber(productsPageNumber);
        return productsList(model, productListPageHelper);
    }

    @RequestMapping
    public String setProductListPageSize(Model model, @ModelAttribute ProductListPageHelper productListPageHelper, @RequestParam int productsPageSize) {
        productListPageHelper.setPageSize(productsPageSize);
        return productsList(model, productListPageHelper);
    }
}

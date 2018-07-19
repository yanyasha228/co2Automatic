package com.example.co2Automatic.controllers;

import com.example.co2Automatic.ControllerHelpers.ProductListPageHelper;
import com.example.co2Automatic.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("products")
public class ProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private ProductListPageHelper productListPageHelper;

    @RequestMapping
    public String productsList(Model model,
                               HttpSession httpSession,
                               @RequestParam(required = false) Integer productsPageNumber,
                               @RequestParam(required = false) Integer productsPageSize,
                               @RequestParam(required = false) Integer productsStockSorting,
                               @RequestParam(required = false) Integer productsCategorySorting) {


        /**
         * Delegate showing product list logic into the {@link ProductListPageHelper}
         */
        productListPageHelper.validatePageShowingState(productsPageNumber, productsPageSize, productsStockSorting, productsCategorySorting);

        model.addAttribute("productList",
                productService.findProductsWithFilteringAndPagination(productListPageHelper));
//            model.addAttribute("productsList",
//                    productService.findProductsByProductStockWithPagination(productListPageHelper.getProductsStockSorting(), PageRequest.of(productListPageHelper.getCurrentPageNumber(),
//                            productListPageHelper.getPageSize(),
//                            Sort.Direction.ASC,
//                            "id")));


        model.addAttribute("productListPageHelper", productListPageHelper);
        return "products";
    }

}

package com.example.co2Automatic.controllers;

import com.example.co2Automatic.ControllerHelpers.ProductListPageHelper;
import com.example.co2Automatic.SystemComponents.AdminSettings;
import com.example.co2Automatic.services.ProductCategoryService;
import com.example.co2Automatic.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    AdminSettings adminSettings;

    @Autowired
    private ProductListPageHelper productListPageHelper;

    @RequestMapping
    public String productsList(Model model,
                               HttpSession httpSession,
                               @RequestParam(required = false) Integer productsPageNumber,
                               @RequestParam(required = false) Integer productsPageSize,
                               @RequestParam(required = false) Integer productsStockSorting,
                               @RequestParam(required = false) Integer productsCategorySorting,
                               @RequestParam(required = false) String searchParam) {


        /**
         * Delegate showing product list logic into the {@link ProductListPageHelper}
         */
        productListPageHelper.validatePageShowingState(productsPageNumber, productsPageSize, productsStockSorting, productsCategorySorting , searchParam);

        Double eurCurrency = adminSettings.getEur_currency();
        Double usdCurrency = adminSettings.getUsd_currency();

        model.addAttribute("productList",
                productService.findProductsWithPagination(productListPageHelper));
//            model.addAttribute("productsList",
//                    productService.findProductsByProductStockWithPagination(productListPageHelper.getProductsStockSorting(), PageRequest.of(productListPageHelper.getCurrentPageNumber(),
//                            productListPageHelper.getPageSize(),
//                            Sort.Direction.ASC,
//                            "id")));


        model.addAttribute("productListPageHelper", productListPageHelper);
        model.addAttribute("productCategories" , productCategoryService.findAll());
        return "products";
    }

}

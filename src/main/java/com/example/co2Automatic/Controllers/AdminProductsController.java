package com.example.co2Automatic.Controllers;

import com.example.co2Automatic.ControllerHelpers.ProductListPageHelper;
import com.example.co2Automatic.SystemComponents.ApplicationSettingsData;
import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.models.ProductStock;
import com.example.co2Automatic.services.ProductCategoryService;
import com.example.co2Automatic.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/products")
public class AdminProductsController {


    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    ApplicationSettingsData applicationSettingsData;

//    @Autowired
//    private ProductListPageHelper productListPageHelper;

    @RequestMapping
    public String productsList(Model model,
                               @PageableDefault(sort = {"id"} , direction = Sort.Direction.ASC ,size = 5) Pageable pageable,
                               @RequestParam Optional<ProductStock> productStockSorting,
                               @RequestParam Optional<Integer> productCategorySortingId,
                               @RequestParam Optional<String> productNameSearchInput) {


        /**
         * Delegate showing product list logic into the {@link ProductListPageHelper}
         */
//        productListPageHelper.validatePageShowingState(productsPageNumber, productsPageSize, productsStockSorting, productsCategorySortingId , productNameSearchInput);
        Page<Product> productsPage = productService.findProductsWithPagination(pageable,
                productStockSorting.orElse(null),
                productCategoryService.findById(productCategorySortingId.orElse(0)).orElse(null),
                productNameSearchInput.orElse(""));

//        Double eurCurrency = adminSettings.getEur_currency();
//        Double usdCurrency = adminSettings.getUsd_currency();

        model.addAttribute("productStockSorting" , productStockSorting.orElse(null));

        model.addAttribute("productCategorySortingId" , productCategorySortingId.orElse(0));

        model.addAttribute("productNameSearchInput" , productNameSearchInput.orElse(""));

        model.addAttribute("productsPage",
                productsPage);
//            model.addAttribute("productsList",
//                    productService.findProductsByProductStockWithPagination(productListPageHelper.getProductsStockSorting(), PageRequest.of(productListPageHelper.getCurrentPageNumber(),
//                            productListPageHelper.getPageSize(),
//                            Sort.Direction.ASC,
//                            "id")));

        model.addAttribute("productCategories" , productCategoryService.findAll());

        return "products";
    }

    @PostMapping("delete")
    String deleteProduct(Model model, @RequestParam Long id){

        if(productService.findById(id).isPresent()){
            productService.deleteById(id);
        }

        return "redirect:../products";
    }


}

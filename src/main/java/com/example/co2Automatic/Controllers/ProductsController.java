package com.example.co2Automatic.Controllers;

import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.models.ModelEnums.ProductStock;
import com.example.co2Automatic.services.AppSettingsModelService;
import com.example.co2Automatic.services.ProductCategoryService;
import com.example.co2Automatic.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductsController {


    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private AppSettingsModelService appSettingsModelService;

//    @Autowired
//    private ProductListPageHelper productListPageHelper;


    @GetMapping
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

//        Double eurCurrency = adminSettings.getEurCurrency();
//        Double usdCurrency = adminSettings.getUsdCurrency();

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



}

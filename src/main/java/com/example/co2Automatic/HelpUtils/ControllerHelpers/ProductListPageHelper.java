//package com.example.co2Automatic.HelpUtils.ControllerHelpers;
//
//import com.example.co2Automatic.models.ProductCategory;
//import com.example.co2Automatic.models.ModelEnums.ProductStock;
//import com.example.co2Automatic.models.ModelEnums.MoneyCurrency;
//import com.example.co2Automatic.services.ProductCategoryService;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.annotation.SessionScope;
//
//@Component
//@SessionScope
//@Data
//public class ProductListPageHelper {
////
////    //Category sorting constants
////    static final int ALL_CATEGORY_SORTING = 1;
////    static final int AIR_RIFLES_CATEGORY_SORTING = 2;
////    static final int STARTING_WEAPON_CATEGORY_SORTING = 3;
////    static final int GAS_GUNS_CATEGORY_SORTING = 4;
////    static final int SPARES_CATEGORY_SORTING = 5;
////    static final int OPTICS_CATEGORY_SORTING = 6;
////    static final int CARE_PRODUCTS_CATEGORY_SORTING = 7;
////    static final int BULLETS_CATEGORY_SORTING = 8;
////    static final int FLAUBERTS_WEAPON_CATEGORY_SORTING = 9;
////    static final int SOUVENIRS_CATEGORY_SORTING = 10;
////
//    //Stock sorting constants
//
//    static final int ALL_STOCK_SORTING = 1;
//    static final int CO2_STOCK_SORTING = 2;
//    static final int B_STOCK_SORTING = 3;
//
//    @Autowired
//    ProductCategoryService productCategoryService;
//
//    public ProductListPageHelper() {
//        this.currentPageNumber = 0;
//        this.pageSize = 5;
//        this.moneyCurrency = MoneyCurrency.UAH;
//    }
//
////    public ProductListPageHelper(int currentPageNumber, int pageSize ) {
////        this.currentPageNumber = currentPageNumber;
////        this.pageSize = pageSize;
////    }
//
//    private int currentPageNumber;
//
//    private int pageSize;
//
//    private int totalPagesAmount;
//
//    private ProductStock productsStockSorting;
//
//    private ProductCategory productCategoryAll = new ProductCategory(0, "Все");
//
//    private ProductCategory productsCategorySorting = productCategoryAll;
//
//    private String productListSearchParam;
//
//    private MoneyCurrency moneyCurrency;
//
//    private void refreshParams() {
//        this.currentPageNumber = 0;
//        this.pageSize = 5;
//        this.totalPagesAmount = 0;
//        this.productsStockSorting = null;
//        this.productsCategorySorting = productCategoryAll;
//        this.productListSearchParam = null;
//    }
//
//    public void validatePageShowingState(Integer productsPageNumber, Integer productsPageSize, Integer productsStockSorting, Integer productsCategory, String searchParam) {
//        if (productsPageNumber == null &&
//                productsPageSize == null &&
//                productsStockSorting == null &&
//                productsCategory == null &&
//                searchParam == null) refreshParams();
//
//
//        if (productsPageNumber != null && productsPageNumber >= 0 && !((productsPageNumber + 1) > totalPagesAmount)) {
//            this.setCurrentPageNumber(productsPageNumber);
//        }
//
//        if (productsPageSize != null) {
//            this.setCurrentPageNumber(0);
//            this.setPageSize(productsPageSize);
//        }
//
//        if (searchParam != null) {
//            if (productListSearchParam == null) refreshParams();
//            this.setProductListSearchParam(searchParam);
//            if (searchParam.replaceAll(" ", "").equalsIgnoreCase("")) refreshParams();
//        }
//
///**
// * Change sorting param {@link ProductListPageHelper}
// */
//        if (productsStockSorting != null) {
//            this.setCurrentPageNumber(0);
//            switch (productsStockSorting) {
//                case CO2_STOCK_SORTING:
//                    this.setProductsStockSorting(ProductStock.CO2_STOCK);
//                    break;
//                case B_STOCK_SORTING:
//                    this.setProductsStockSorting(ProductStock.B_STOCK);
//                    break;
//                case ALL_STOCK_SORTING:
//                    this.setProductsStockSorting(null);
//                    break;
//            }
//        }
//        if (productsCategory != null) {
//            this.setCurrentPageNumber(0);
//
//            if(productsCategory==0){
//                this.setProductsCategorySorting(productCategoryAll);
//            }else if (productCategoryService.exist(productsCategory)){
//                this.setProductsCategorySorting(productCategoryService.findOne(productsCategory));
//            }else{
//                this.setProductsCategorySorting(productCategoryAll);
//            }
////            switch (productsCategory) {
////
////                case ALL_CATEGORY_SORTING:
////                    this.setProductsCategorySorting(null);
////                    break;
////                case AIR_RIFLES_CATEGORY_SORTING:
////                    this.setProductsCategorySorting(ProductCategory.AIR_RIFLES);
////                    break;
////
////                case STARTING_WEAPON_CATEGORY_SORTING:
////                    this.setProductsCategorySorting(ProductCategory.STARTING_WEAPON);
////                    break;
////
////                case GAS_GUNS_CATEGORY_SORTING:
////                    this.setProductsCategorySorting(ProductCategory.GAS_GUNS);
////                    break;
////
////                case SPARES_CATEGORY_SORTING:
////                    this.setProductsCategorySorting(ProductCategory.SPARES);
////                    break;
////
////                case OPTICS_CATEGORY_SORTING:
////                    this.setProductsCategorySorting(ProductCategory.OPTICS);
////                    break;
////
////                case CARE_PRODUCTS_CATEGORY_SORTING:
////                    this.setProductsCategorySorting(ProductCategory.CARE_PRODUCTS);
////                    break;
////
////                case BULLETS_CATEGORY_SORTING:
////                    this.setProductsCategorySorting(ProductCategory.BULLETS);
////                    break;
////
////                case FLAUBERTS_WEAPON_CATEGORY_SORTING:
////                    this.setProductsCategorySorting(ProductCategory.FLAUBERTS_WEAPON);
////                    break;
////
////                case SOUVENIRS_CATEGORY_SORTING:
////                    this.setProductsCategorySorting(ProductCategory.SOUVENIRS);
////                    break;
////
////            }
//        }
//    }
//}

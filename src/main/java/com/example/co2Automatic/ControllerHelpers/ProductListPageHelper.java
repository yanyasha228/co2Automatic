package com.example.co2Automatic.ControllerHelpers;

public class ProductListPageHelper {

    public ProductListPageHelper() {
    }

    public ProductListPageHelper(int currentPageNumber, int pageSize) {
        this.currentPageNumber = currentPageNumber;
        this.pageSize = pageSize;
    }


    private int currentPageNumber;
    private int pageSize;

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

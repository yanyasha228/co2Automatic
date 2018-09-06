package com.example.co2Automatic.models;

public enum MoneyCurrency {
    UAH, USD, EUR;

    public String getCurrencySymbol() {
        String curSymb = "";
        switch (name()) {
            case "UAH":
                curSymb = "₴";
                break;
            case "USD":
                curSymb = "$";
                break;
            case "EUR":
                curSymb = "€";
                break;
        }
        return curSymb;
    }
}

package com.example.co2Automatic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/settings/exchangeRates")
public class AdminSettingsExchangeRatesController {

    @RequestMapping
    public String adminSettingsExchangeRates(Model model){
        return "adminSettingsExchangeRates";
    }

    @PostMapping("submit")
    public String updateSettings(Model model){

        return "redirect:../admin/settings";
    }
}

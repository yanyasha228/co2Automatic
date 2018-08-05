package com.example.co2Automatic.controllers;

import com.example.co2Automatic.SystemComponents.AdminSettings;
import com.example.co2Automatic.models.SessionModels.AdminModelSettings;
import com.example.co2Automatic.services.AdminModelSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin/settings/exchangeRates")
public class AdminSettingsExchangeRatesController {

    @Autowired
    private AdminModelSettingsService adminModelSettingsService;

    @Autowired
    private AdminSettings adminSettings;

    @RequestMapping
    public String adminSettingsExchangeRates(Model model){
        model.addAttribute("adminSettings" , adminSettings);
        return "adminSettingsExchangeRates";
    }

    @PostMapping("submit")
    public String updateSettings(Model model , @RequestParam(required = false) Double usdCurrency,
                                 @RequestParam(required = false) Double eurCurrency){

        AdminModelSettings adminModelSettings = adminModelSettingsService.getSettings();

        if (usdCurrency!=null)
        adminModelSettings.setUsd_currency(usdCurrency);
        if (eurCurrency!=null)
        adminModelSettings.setEur_currency(eurCurrency);

        adminModelSettingsService.updateSettings(adminModelSettings);

        adminSettings.setSettings(adminModelSettingsService.getSettings());

        return "redirect:../";
    }
}

package com.example.co2Automatic.controllers;

import com.example.co2Automatic.SystemComponents.ApplicationSettingsData;
import com.example.co2Automatic.models.SessionModels.AdminSettingsModel;
import com.example.co2Automatic.models.SessionModels.ApplicationSettingsDataModel;
import com.example.co2Automatic.services.ApplicationSettingsDataModelService;
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
    private ApplicationSettingsDataModelService applicationSettingsDataModelService;

    @Autowired
    private ApplicationSettingsData applicationSettingsData;

    @RequestMapping
    public String adminSettingsExchangeRates(Model model) {
        model.addAttribute("applicationSettingsData", applicationSettingsData);
        return "adminSettingsExchangeRates";
    }

    @PostMapping("submit")
    public String updateSettings(Model model, @RequestParam(required = false) Double usdCurrency,
                                 @RequestParam(required = false) Double eurCurrency) {

        if (usdCurrency != null)
            applicationSettingsData.setUsd_currency(usdCurrency);
        if (eurCurrency != null)
            applicationSettingsData.setEur_currency(eurCurrency);

        applicationSettingsData.saveApplicationSettingsData();

        return "redirect:../";
    }
}

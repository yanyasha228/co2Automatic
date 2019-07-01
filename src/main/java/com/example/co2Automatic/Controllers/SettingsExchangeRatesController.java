package com.example.co2Automatic.Controllers;

import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleEntityUpdatingException;
import com.example.co2Automatic.models.SessionModels.AppSettingsModel;
import com.example.co2Automatic.services.AppSettingsModelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("settings/exchangeRates")
public class SettingsExchangeRatesController {

    private static final Logger logger = Logger.getLogger(SettingsExchangeRatesController.class);

    @Autowired
    private AppSettingsModelService appSettingsModelService;

    @RequestMapping
    public String adminSettingsExchangeRates(Model model) {
        model.addAttribute("appSettingsModel", appSettingsModelService.getSettings());
        return "settingsExchangeRates";
    }

    @PostMapping("submit")
    public String updateSettings(Model model, @ModelAttribute AppSettingsModel appSettingsModel) throws ImpossibleEntityUpdatingException {

        if(appSettingsModel!=null)
        appSettingsModelService.update(appSettingsModel);

        return "redirect:../";
    }


    @ExceptionHandler(ImpossibleEntityUpdatingException.class)
    public String handleOrderManipulationException(Model model, Exception ex) {
        logger.error(ex.toString());

        model.addAttribute("error", ex.getClass().getName());

        return "error";
    }
}

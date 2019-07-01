package com.example.co2Automatic.Controllers;

import com.example.co2Automatic.services.AppSettingsModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("settings")
public class SettingsController {

    @Autowired
    private AppSettingsModelService appSettingsModelService;

    @RequestMapping
    public String adminSettings(Model model) {

        model.addAttribute("appSettingsModel" , appSettingsModelService.getSettings());
        return "settings";
    }


}

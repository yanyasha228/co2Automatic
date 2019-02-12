package com.example.co2Automatic.Controllers;

import com.example.co2Automatic.services.AdminSettingsModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/settings")
public class AdminSettingsController {

    @Autowired
    private AdminSettingsModelService adminSettingsModelService;

    @RequestMapping
    public String adminSettings(Model model){
        return "adminSettings";
    }


}
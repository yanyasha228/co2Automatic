package com.example.co2Automatic.controllers;

import com.example.co2Automatic.services.AdminModelSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/settings")
public class AdminSettingsController {

    @Autowired
    private AdminModelSettingsService adminModelSettingsService;

    @RequestMapping
    public String adminSettings(Model model){
        return "adminSettings";
    }


}

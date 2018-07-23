package com.example.co2Automatic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/settings")
public class AdminSettingsController {

    @RequestMapping
    public String adminSettings(Model model){
        return null;
    }
}

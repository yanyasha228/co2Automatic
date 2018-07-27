package com.example.co2Automatic.configs;

import com.example.co2Automatic.SystemComponents.AdminSettings;
import com.example.co2Automatic.models.SessionModels.AdminModelSettings;
import com.example.co2Automatic.services.AdminModelSettingsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class SettingsBeanConfig {
//
//    @Bean
//    @DependsOn("CRunner")
//    public AdminSettings getAdminSettings(AdminModelSettingsService adminModelSettingsService){
//        AdminSettings adminSettings = new AdminSettings();
//        AdminModelSettings adminModelSettings = adminModelSettingsService.getSettings();
//        adminSettings.setEur_currency(adminModelSettings.getEur_currency());
//        adminSettings.setUsd_currency(adminModelSettings.getUsd_currency());
//
//        return adminSettings;
//    }
}

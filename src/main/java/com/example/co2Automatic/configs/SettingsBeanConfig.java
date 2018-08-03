package com.example.co2Automatic.configs;

import com.example.co2Automatic.SystemComponents.AdminSettings;
import com.example.co2Automatic.models.SessionModels.AdminModelSettings;
import com.example.co2Automatic.services.AdminModelSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.ApplicationScope;

@Configuration
public class SettingsBeanConfig {

    @Autowired
    AdminModelSettingsService adminModelSettingsService;


    @Bean("adminSettings")
    @ApplicationScope
    @Transactional
    public AdminSettings adminSettings() {
        AdminSettings adminSettings = new AdminSettings();

if(!adminModelSettingsService.exist()) {

    AdminModelSettings adminModelSettings = new AdminModelSettings();
    adminModelSettings.setEur_currency(33.2);
    adminModelSettings.setUsd_currency(28);

    adminModelSettingsService.updateSettings(adminModelSettings);
}

adminSettings.setSettings(adminModelSettingsService.getSettings());

        return adminSettings;
    }
}

package com.example.co2Automatic.configs;

import com.example.co2Automatic.SystemComponents.AdminSettings;
import com.example.co2Automatic.SystemComponents.ApplicationSettingsData;
import com.example.co2Automatic.models.SessionModels.AdminSettingsModel;
import com.example.co2Automatic.models.SessionModels.ApplicationSettingsDataModel;
import com.example.co2Automatic.services.AdminSettingsModelService;
import com.example.co2Automatic.services.ApplicationSettingsDataModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.ApplicationScope;

@Configuration
public class SettingsBeanConfig {

    @Autowired
    ApplicationSettingsDataModelService applicationSettingsDataModelService;
//    @Autowired
//    AdminSettingsModelService adminSettingsModelService;
//
//
//    @Bean("adminSettings")
//    @ApplicationScope
//    @Transactional
//    public AdminSettings adminSettings() {
//        AdminSettings adminSettings = new AdminSettings();
//
//        if (!adminSettingsModelService.exist()) {
//
//            AdminSettingsModel adminSettingsModel = new AdminSettingsModel();
//            adminSettingsModel.setEur_currency(33.2);
//            adminSettingsModel.setUsd_currency(28);
//
//            adminSettingsModelService.updateSettings(adminSettingsModel);
//        }
//
//        adminSettings.setSettings(adminSettingsModelService.getSettings());
//
//        return adminSettings;
//    }

//    @Bean("applicationSettingsData")
//    @ApplicationScope
//    @Transactional
//    public ApplicationSettingsData applicationSettingsData() {
//
//        ApplicationSettingsData applicationSettingsData = new ApplicationSettingsData();
//
//        if (!applicationSettingsDataModelService.exist()) {
//
//            ApplicationSettingsDataModel applicationSettingsDataModel = new ApplicationSettingsDataModel();
//            applicationSettingsDataModel.setEur_currency(33.2);
//            applicationSettingsDataModel.setUsd_currency(28);
//
//            applicationSettingsDataModelService.updateSettings(applicationSettingsDataModel);
//        }
//
//        applicationSettingsData.setSettings(applicationSettingsDataModelService.getSettings());
//
//        return applicationSettingsData;
//    }
//    @Bean("applicationSettingsData")
//    @ApplicationScope
//    @Transactional
//    public ApplicationSettingsData applicationSettingsData() {
//        return new ApplicationSettingsData();
//    }

}

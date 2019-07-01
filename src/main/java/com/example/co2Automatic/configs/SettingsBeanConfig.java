package com.example.co2Automatic.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SettingsBeanConfig {


//    @Autowired
//    AppSettingsModelService adminSettingsModelService;
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
//            AppSettingsModel adminSettingsModel = new AppSettingsModel();
//            adminSettingsModel.setEurCurrency(33.2);
//            adminSettingsModel.setUsdCurrency(28);
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
//            applicationSettingsDataModel.setEurCurrency(33.2);
//            applicationSettingsDataModel.setUsdCurrency(28);
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

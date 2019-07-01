//package com.example.co2Automatic.SystemComponents;
//
//import com.example.co2Automatic.models.SessionModels.ApplicationSettingsDataModel;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.annotation.ApplicationScope;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//
//@Data
//@Component("applicationSettingsData")
//@DependsOn("applicationSettingsDataModelService")
//@ApplicationScope
//public class ApplicationSettingsData {
//
//    @Autowired
//    private ApplicationSettingsDataModelService applicationSettingsDataModelService;
//
//    private double usdCurrency;
//
//    private double eurCurrency;
//
//    public void saveApplicationSettingsData() {
//
//        ApplicationSettingsDataModel applicationSettingsDataModel = applicationSettingsDataModelService.getSettings();
//
//
//        applicationSettingsDataModel.setEurCurrency(this.eurCurrency);
//        applicationSettingsDataModel.setUsdCurrency(this.usdCurrency);
//
//        applicationSettingsDataModelService.updateSettings(applicationSettingsDataModel);
//    }
//
//    @PostConstruct
//    private void initApplicationSettingsData() {
//
//        if (!applicationSettingsDataModelService.exist()) {
//            ApplicationSettingsDataModel applicationSettingsDataModel = new ApplicationSettingsDataModel();
//            applicationSettingsDataModel.setEurCurrency(33.2);
//            applicationSettingsDataModel.setUsdCurrency(28);
//            applicationSettingsDataModelService.updateSettings(applicationSettingsDataModel);
//        }
//
//        updateApplicationSettingsData();
//    }
//
//    @PreDestroy
//    private void destroyApplicationSettingsData() {
//        saveApplicationSettingsData();
//    }
//
//
//    private void updateApplicationSettingsData() {
//        ApplicationSettingsDataModel applicationSettingsDataModel = applicationSettingsDataModelService.getSettings();
//        this.setEurCurrency(applicationSettingsDataModel.getEurCurrency());
//        this.setUsdCurrency(applicationSettingsDataModel.getUsdCurrency());
//
//    }
//}

package com.example.co2Automatic.SystemComponents;

import com.example.co2Automatic.models.SessionModels.ApplicationSettingsDataModel;
import com.example.co2Automatic.services.ApplicationSettingsDataModelService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
@Component("applicationSettingsData")
@DependsOn("applicationSettingsDataModelService")
@ApplicationScope
public class ApplicationSettingsData {

    @Autowired
    private ApplicationSettingsDataModelService applicationSettingsDataModelService;

    private double usd_currency;

    private double eur_currency;

    public void saveApplicationSettingsData() {

        ApplicationSettingsDataModel applicationSettingsDataModel = applicationSettingsDataModelService.getSettings();


        applicationSettingsDataModel.setEur_currency(this.eur_currency);
        applicationSettingsDataModel.setUsd_currency(this.usd_currency);

        applicationSettingsDataModelService.updateSettings(applicationSettingsDataModel);
    }

    @PostConstruct
    private void initApplicationSettingsData() {

        if (!applicationSettingsDataModelService.exist()) {
            ApplicationSettingsDataModel applicationSettingsDataModel = new ApplicationSettingsDataModel();
            applicationSettingsDataModel.setEur_currency(33.2);
            applicationSettingsDataModel.setUsd_currency(28);
            applicationSettingsDataModelService.updateSettings(applicationSettingsDataModel);
        }

        updateApplicationSettingsData();
    }

    @PreDestroy
    private void destroyApplicationSettingsData() {
        saveApplicationSettingsData();
    }


    private void updateApplicationSettingsData() {
        ApplicationSettingsDataModel applicationSettingsDataModel = applicationSettingsDataModelService.getSettings();
        this.setEur_currency(applicationSettingsDataModel.getEur_currency());
        this.setUsd_currency(applicationSettingsDataModel.getUsd_currency());

    }
}

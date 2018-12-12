package com.example.co2Automatic.services;

import com.example.co2Automatic.models.SessionModels.ApplicationSettingsDataModel;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ApplicationSettingsDataModelService {

    void updateSettings(ApplicationSettingsDataModel applicationSettingsDataModel);

    ApplicationSettingsDataModel getSettings();

    boolean exist();
}

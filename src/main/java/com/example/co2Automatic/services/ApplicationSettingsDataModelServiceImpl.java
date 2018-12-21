package com.example.co2Automatic.services;

import com.example.co2Automatic.Dao.ApplicationSettingsDataModelDao;
import com.example.co2Automatic.models.SessionModels.ApplicationSettingsDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("applicationSettingsDataModelService")
public class ApplicationSettingsDataModelServiceImpl implements ApplicationSettingsDataModelService {

    @Autowired
    ApplicationSettingsDataModelDao applicationSettingsDataModelDao;

    @Override
    public void updateSettings(ApplicationSettingsDataModel applicationSettingsDataModel) {
        applicationSettingsDataModelDao.save(applicationSettingsDataModel);
    }

    @Override
    public ApplicationSettingsDataModel getSettings() {
        return applicationSettingsDataModelDao.findById(1L).orElse(null);
    }

    @Override
    public boolean exist() {
        return applicationSettingsDataModelDao.existsById(1L);
    }
}

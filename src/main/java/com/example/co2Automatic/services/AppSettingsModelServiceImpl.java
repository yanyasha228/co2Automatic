package com.example.co2Automatic.services;

import com.example.co2Automatic.Dao.AppSettingsDao;
import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleEntityUpdatingException;
import com.example.co2Automatic.models.SessionModels.AppSettingsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("adminModelSettingsService")
public class AppSettingsModelServiceImpl implements AppSettingsModelService {
    @Autowired
    private AppSettingsDao appSettingsDao;


    @Override
    public AppSettingsModel update(AppSettingsModel appSettingsModel) throws ImpossibleEntityUpdatingException {

        if (appSettingsModel.getId() != 1L)
            throw new ImpossibleEntityUpdatingException("Attempt to update entity without ID!!!");
            return appSettingsDao.save(appSettingsModel);


    }

    @Override
    public Optional<AppSettingsModel> getSettings() {
        return appSettingsDao.findById(1L);
    }

    @Override
    public boolean exist() {
        return appSettingsDao.existsById(1L);
    }
}

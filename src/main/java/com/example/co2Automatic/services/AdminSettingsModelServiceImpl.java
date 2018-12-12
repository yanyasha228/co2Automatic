package com.example.co2Automatic.services;

import com.example.co2Automatic.dao.AdminModelSettingsDao;
import com.example.co2Automatic.models.SessionModels.AdminSettingsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminModelSettingsService")
public class AdminSettingsModelServiceImpl implements AdminSettingsModelService {
    @Autowired
    private AdminModelSettingsDao adminModelSettingsDao;

    @Override
    public void updateSettings(AdminSettingsModel adminSettingsModel) {
        adminModelSettingsDao.save(adminSettingsModel);
    }

    @Override
    public AdminSettingsModel getSettings(){
       return adminModelSettingsDao.getOne(1L);
    }

    @Override
    public boolean exist() {
        return adminModelSettingsDao.existsById(1L);
    }
}

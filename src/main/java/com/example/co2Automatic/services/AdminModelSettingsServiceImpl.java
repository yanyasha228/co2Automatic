package com.example.co2Automatic.services;

import com.example.co2Automatic.SystemComponents.AdminSettings;
import com.example.co2Automatic.dao.AdminModeSettingsDao;
import com.example.co2Automatic.models.SessionModels.AdminModelSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("adminModelSettingsService")
public class AdminModelSettingsServiceImpl implements AdminModelSettingsService {
    @Autowired
    private AdminModeSettingsDao adminModeSettingsDao;

    @Override
    public void updateSettings(AdminModelSettings adminModelSettings) {
        adminModeSettingsDao.save(adminModelSettings);
    }

    @Override
    public AdminModelSettings getSettings(){
       return adminModeSettingsDao.getOne(1L);
    }

    @Override
    public boolean exist() {
        return adminModeSettingsDao.existsById(1L);
    }
}

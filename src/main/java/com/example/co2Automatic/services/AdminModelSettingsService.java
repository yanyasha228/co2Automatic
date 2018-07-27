package com.example.co2Automatic.services;

import com.example.co2Automatic.models.SessionModels.AdminModelSettings;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AdminModelSettingsService {

    void updateSettings(AdminModelSettings adminModelSettings);

    AdminModelSettings getSettings();

}

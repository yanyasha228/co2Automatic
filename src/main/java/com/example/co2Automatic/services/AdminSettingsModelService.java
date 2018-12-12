package com.example.co2Automatic.services;

import com.example.co2Automatic.models.SessionModels.AdminSettingsModel;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AdminSettingsModelService {

    void updateSettings(AdminSettingsModel adminSettingsModel);

    AdminSettingsModel getSettings();

    boolean exist();

}

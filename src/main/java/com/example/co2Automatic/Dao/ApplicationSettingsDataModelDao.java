package com.example.co2Automatic.Dao;

import com.example.co2Automatic.models.SessionModels.ApplicationSettingsDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationSettingsDataModelDao extends JpaRepository<ApplicationSettingsDataModel,Long> {
}

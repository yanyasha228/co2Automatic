package com.example.co2Automatic.Dao;

import com.example.co2Automatic.models.SessionModels.AppSettingsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppSettingsDao extends JpaRepository<AppSettingsModel,Long> {
}

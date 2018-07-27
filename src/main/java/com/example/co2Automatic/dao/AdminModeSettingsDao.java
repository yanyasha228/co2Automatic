package com.example.co2Automatic.dao;

import com.example.co2Automatic.models.SessionModels.AdminModelSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminModeSettingsDao extends JpaRepository<AdminModelSettings,Long> {
}

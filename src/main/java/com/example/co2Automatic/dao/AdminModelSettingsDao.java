package com.example.co2Automatic.dao;

import com.example.co2Automatic.models.SessionModels.AdminSettingsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminModelSettingsDao extends JpaRepository<AdminSettingsModel,Long> {
}

package com.example.co2Automatic.services;

import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleEntityUpdatingException;
import com.example.co2Automatic.models.SessionModels.AppSettingsModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface AppSettingsModelService {

    AppSettingsModel update(AppSettingsModel appSettingsModel) throws ImpossibleEntityUpdatingException;


    Optional<AppSettingsModel> getSettings();

    boolean exist();

}

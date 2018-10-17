package com.example.co2Automatic.services;

import com.example.co2Automatic.models.PhoneNumber;

import java.util.List;
import java.util.Optional;

public interface PhoneNumberService {
    void save(PhoneNumber phoneNumber);

    Optional<PhoneNumber> getPhoneNumberByPhoneNumber(String phoneNumber);

    List<PhoneNumber> getPhoneNumbersByNoNFullPhoneNumber(String nonFullPhoneNumber);

}

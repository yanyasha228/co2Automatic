package com.example.co2Automatic.services;

import com.example.co2Automatic.models.PhoneNumber;

public interface PhoneNumberService {
    void save(PhoneNumber phoneNumber);
    PhoneNumber getPhoneNumber(long phoneNumber);
}

package com.example.co2Automatic.services;

import com.example.co2Automatic.Dao.PhoneNumberDao;
import com.example.co2Automatic.models.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    @Autowired
    PhoneNumberDao phoneNumberDao;

    @Override
    public void save(PhoneNumber phoneNumber) {
        phoneNumberDao.save(phoneNumber);
    }

    @Override
    public Optional<PhoneNumber> getPhoneNumberByPhoneNumber(String phoneNumber) {
        return phoneNumberDao.findPhoneNumberByPhoneNumber(phoneNumber);
    }

    @Override
    public List<PhoneNumber> getPhoneNumbersByNoNFullPhoneNumber(String nonFullPhoneNumber) {
        return phoneNumberDao.findPhoneNumbersByPhoneNumber(nonFullPhoneNumber);
    }

}

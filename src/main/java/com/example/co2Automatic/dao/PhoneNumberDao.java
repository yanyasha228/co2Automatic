package com.example.co2Automatic.dao;

import com.example.co2Automatic.models.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumberDao extends JpaRepository<PhoneNumber,Long> {
}

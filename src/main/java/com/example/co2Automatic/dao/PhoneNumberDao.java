package com.example.co2Automatic.dao;

import com.example.co2Automatic.models.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhoneNumberDao extends JpaRepository<PhoneNumber, Long> {
    @Query("select pn from PhoneNumber pn where pn.phoneNumber like :nonFullNum")
    List<PhoneNumber> findPhoneNumberByNonFullNum(@Param("nonFullNum") String nonFullNum);
}

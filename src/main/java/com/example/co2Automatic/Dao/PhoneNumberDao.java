package com.example.co2Automatic.Dao;

import com.example.co2Automatic.models.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhoneNumberDao extends JpaRepository<PhoneNumber, Long> {
//    @Query("select pn from PhoneNumber pn where pn.phoneNumber like :nonFullNum")
//    List<PhoneNumber> findPhoneNumberByNonFullNum(@Param("nonFullNum") String nonFullNum);
    List<PhoneNumber> findPhoneNumbersByPhoneNumberLike(String phoneNumber);

    Optional<PhoneNumber> findPhoneNumberByPhoneNumber(String phoneNumber);

    List<PhoneNumber> findPhoneNumbersByPhoneNumber(String nonFullPhoneNumber);

}

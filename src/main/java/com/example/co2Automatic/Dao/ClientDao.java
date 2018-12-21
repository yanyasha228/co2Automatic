package com.example.co2Automatic.Dao;

import com.example.co2Automatic.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientDao extends JpaRepository<Client,Long> {

    List<Client> findClientsByPhoneNumberIgnoreCaseContaining(String phoneNumber);

    Optional<Client> findClientByPhoneNumber(String phoneNumber);
}

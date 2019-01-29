package com.example.co2Automatic.services;

import com.example.co2Automatic.models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    void save(Client client);

    List<Client> findClientsByNoNFullPhoneNumber(String nonFullPhoneNumber);
    Page<Client> findAllWithPagination(Pageable pageable);

    Optional<Client> findClientByPhoneNumber(String phoneNumber);

    Optional<Client> findById(long l);

    Client saveAndReturnEntity(Client client);

    List<Client> findAll();

    void deleteById(Long id);
}

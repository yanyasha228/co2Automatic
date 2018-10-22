package com.example.co2Automatic.services;

import com.example.co2Automatic.models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    void save(Client client);

    Page<Client> findAllWithPagination(Pageable pageable);

    Optional<Client> findById(long l);

    List<Client> findAll();
}

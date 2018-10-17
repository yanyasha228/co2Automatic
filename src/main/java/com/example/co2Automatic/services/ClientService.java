package com.example.co2Automatic.services;

import com.example.co2Automatic.models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    void save(Client client);

    Page<Client> findAllWithPagination(Pageable pageable);
}

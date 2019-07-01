package com.example.co2Automatic.services;

import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleEntityUpdatingException;
import com.example.co2Automatic.models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Client save(Client client);

    List<Client> save(List<Client> clients);

    Client update(Client client) throws ImpossibleEntityUpdatingException;

    List<Client> update(List<Client> clients) throws ImpossibleEntityUpdatingException;

    List<Client> findClientsByNoNFullPhoneNumber(String nonFullPhoneNumber);

    Page<Client> findAllWithPagination(Pageable pageable);

    Optional<Client> findClientByPhoneNumber(String phoneNumber);

    Optional<Client> findById(Long l);

    boolean exists(Client client);

    List<Client> findAll();

    void delete(Long id);

    void delete(Client client);

    void delete(List<Client> clients);

}

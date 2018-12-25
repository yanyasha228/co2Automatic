package com.example.co2Automatic.services;

import com.example.co2Automatic.Dao.ClientDao;
import com.example.co2Automatic.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientDao clientDao;

    @Override
    public void save(Client client) {
        clientDao.save(client);
    }

    @Override
    public List<Client> findClientsByNoNFullPhoneNumber(String nonFullPhoneNumber) {
        return clientDao.findClientsByPhoneNumberIgnoreCaseContaining(nonFullPhoneNumber);
    }

    @Override
    public Page<Client> findAllWithPagination(Pageable pageable) {
        return clientDao.findAll(pageable);
    }

    @Override
    public Optional<Client> findClientByPhoneNumber(String phoneNumber) {
        return clientDao.findClientByPhoneNumber(phoneNumber);
    }


    @Override
    public Optional<Client> findById(long l) {
        return clientDao.findById(l);
    }

    @Override
    public Client saveAndReturnEntity(Client client) {
        return clientDao.save(client);
    }

    @Override
    public List<Client> findAll() {
        return clientDao.findAll();
    }
}

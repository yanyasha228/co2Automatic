package com.example.co2Automatic.services;

import com.example.co2Automatic.dao.ClientDao;
import com.example.co2Automatic.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientDao clientDao;

    @Override
    public void save(Client client) {
        clientDao.save(client);
    }
}

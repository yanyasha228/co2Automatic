package com.example.co2Automatic.services;

import com.example.co2Automatic.Dao.ClientDao;
import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleEntityUpdatingException;
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
    private ClientDao clientDao;

    @Override
    public Client save(Client client) {
        return clientDao.save(client);
    }

    @Override
    public List<Client> save(List<Client> clients) {
        return clientDao.saveAll(clients);
    }

    @Override
    public Client update(Client client) throws ImpossibleEntityUpdatingException {

        if (client.getId() <= 0) throw new ImpossibleEntityUpdatingException("Attempt to update entity without ID!!!");

        return clientDao.save(client);

    }

    @Override
    public List<Client> update(List<Client> clients) throws ImpossibleEntityUpdatingException {

        for (Client oneOf : clients) {
            if (oneOf.getId() <= 0)
                throw new ImpossibleEntityUpdatingException("Attempt to update entity without ID!!!");
        }

        return clientDao.saveAll(clients);
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
    public Optional<Client> findById(Long l) {
        return clientDao.findById(l);
    }

    @Override
    public boolean exists(Client client) {
        if(client.getId()==null || client.getId()==0) return false;
        return clientDao.existsById(client.getId());
    }


    @Override
    public List<Client> findAll() {
        return clientDao.findAll();
    }


    @Override
    public void delete(Client client) {
        clientDao.delete(client);
    }

    @Override
    public void delete(List<Client> clients) {
        clientDao.deleteAll(clients);
    }

    @Override
    public void delete(Long id) {
        clientDao.deleteById(id);
    }

}

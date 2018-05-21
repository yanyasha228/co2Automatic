package com.example.co2Automatic.dao;

import com.example.co2Automatic.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDao extends JpaRepository<Client,Long> {
}

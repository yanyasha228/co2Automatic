package com.example.co2Automatic.Dao;


import com.example.co2Automatic.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

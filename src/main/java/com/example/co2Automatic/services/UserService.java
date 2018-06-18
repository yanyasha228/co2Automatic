package com.example.co2Automatic.services;

import com.example.co2Automatic.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void save(User user);
    void delete(User user);
}

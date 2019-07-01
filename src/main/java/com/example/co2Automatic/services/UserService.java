package com.example.co2Automatic.services;

import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleEntityUpdatingException;
import com.example.co2Automatic.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    User save(User user);

    void delete(User user);

    void delete(List<User> users);

    List<User> update(List<User> users) throws ImpossibleEntityUpdatingException;

    List<User> save(List<User> users);

    User update(User user) throws ImpossibleEntityUpdatingException;

    Optional<User> findById(Long id);




}

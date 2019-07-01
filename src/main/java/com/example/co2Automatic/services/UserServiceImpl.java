package com.example.co2Automatic.services;

import com.example.co2Automatic.Dao.UserDao;
import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleEntityUpdatingException;
import com.example.co2Automatic.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }


    @Override
    public User update(User user) throws ImpossibleEntityUpdatingException{
        if (user.getId() == 0)
        throw new ImpossibleEntityUpdatingException("Attempt to update entity without ID!!!");

        return userDao.save(user);

    }

    @Override
    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public void delete(User user){
        userDao.delete(user);
    }

    @Override
    public void delete(List<User> users) {
        userDao.deleteAll(users);
    }

    @Override
    public List<User> update(List<User> users) throws ImpossibleEntityUpdatingException {
        for ( User oneOf : users) {
            if (oneOf.getId() <= 0)
                throw new ImpossibleEntityUpdatingException("Attempt to update entity without ID!!!");
        }
        return userDao.saveAll(users);
    }

    @Override
    public List<User> save(List<User> users) {
        return userDao.saveAll(users);
    }

}
package com.oren.urlshortener.services;

import com.oren.urlshortener.beans.User;
import com.oren.urlshortener.beans.UserClick;
import com.oren.urlshortener.exceptions.NotExistException;
import com.oren.urlshortener.repositories.UserClickRepo;
import com.oren.urlshortener.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserClickRepo userClickRepo;

    public User addUser(String username) {
        User user = User.builder().username(username).build();
        return userRepo.save(user);
    }

    public void deleteUser(String username) throws NotExistException {
        if (!userRepo.existsByUsername(username)) {
            throw new NotExistException("Username was not found");
        }
        userRepo.deleteByUsername(username);
    }

    public List<User> getAllUsers() throws NotExistException {
        List<User> users = userRepo.findAll();
        if (users.isEmpty()) {
            throw new NotExistException("No users found");
        }
        return users;
    }

    public User getUserByUsername(String username) throws NotExistException {
        return userRepo.findByUsername(username).orElseThrow(() -> new NotExistException("User ID was not found"));
    }

    public List<UserClick> getUserClicks(String username) throws NotExistException {
        List<UserClick> userClicks = userClickRepo.findAllByUsername(username);
        if (userClicks.isEmpty()) {
            throw new NotExistException(("No clicks were used"));
        }
        return userClicks;
    }

}

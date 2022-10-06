package com.oren.urlshortener.repositories;

import com.mongodb.MongoWriteException;
import com.oren.urlshortener.beans.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    void deleteByUsername(String username);

}

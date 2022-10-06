package com.oren.urlshortener.repositories;

import com.oren.urlshortener.beans.UserClick;
import com.oren.urlshortener.beans.UserClickKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.List;

public interface UserClickRepo extends CassandraRepository<UserClick, UserClickKey> {
    @Query(value = "SELECT * FROM user_click WHERE user_name=:username")
    List<UserClick> findAllByUsername(String username);

}

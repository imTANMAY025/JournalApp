package com.tp.journalApp.DAO;

import com.tp.journalApp.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserDAO extends MongoRepository<UserEntity,String> {

    Optional<UserEntity> findByUsername(String username);

    void deleteByUsername(String username);
}


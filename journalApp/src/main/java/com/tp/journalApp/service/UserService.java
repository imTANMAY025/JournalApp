package com.tp.journalApp.service;

import com.tp.journalApp.entity.UserEntity;

import java.util.List;

public interface UserService {

    public UserEntity saveUserData(UserEntity userEntity)throws Exception;

    public List<UserEntity> getAllUsers()throws Exception;

    public UserEntity getUsersById(String username)throws Exception;

    public UserEntity updateUser(UserEntity userEntity, String username)throws Exception;

    public boolean deleteUser(String username)throws Exception;
}

package com.tp.journalApp.service;

import com.tp.journalApp.DAO.UserDAO;
import com.tp.journalApp.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserEntity saveUserData(UserEntity userEntity) throws Exception {

        userEntity.setCreatedDate(String.valueOf(new Date()));


        userEntity = userDAO.save(userEntity);

        return userEntity;
    }

    @Override
    public List<UserEntity> getAllUsers() throws Exception {

        return userDAO.findAll();
    }

    @Override
    public UserEntity getUsersById(String username) throws Exception {

        return userDAO.findByUsername(String.valueOf(username)).orElse(null);
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity, String username) throws Exception {
        userEntity.setCreatedDate(String.valueOf(new Date()));

        UserEntity entryOld= userDAO.findByUsername(String.valueOf(username)).orElse(null);

        if(entryOld!=null){

            entryOld.setPassword(!entryOld.getPassword().equalsIgnoreCase(userEntity.getPassword())
                    ?userEntity.getPassword():entryOld.getPassword());


            userDAO.save(userEntity);

            return userEntity;

        }else{
            return null;
        }
    }

    @Override
    public boolean deleteUser(String username) throws Exception {
        userDAO.deleteByUsername(username);

        return true;
    }
}

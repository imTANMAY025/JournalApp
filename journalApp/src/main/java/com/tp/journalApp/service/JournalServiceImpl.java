package com.tp.journalApp.service;

import com.tp.journalApp.DAO.UserDAO;
import com.tp.journalApp.DAO.JournalDAO;
import com.tp.journalApp.entity.JournalEntity;
import com.tp.journalApp.entity.UserEntity;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class JournalServiceImpl implements JournalService{

    @Autowired
    private JournalDAO journalDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public JournalEntity saveJournalData(JournalEntity entity,String id)throws Exception {

        try{
            entity.setDate(new Date());
            UserEntity userEntity = userService.getUsersById(id);

            JournalEntity savedEntity = journalDAO.save(entity);

            userEntity.getJournalEntityList().add(savedEntity);
            UserEntity savedUserEntity = userDAO.save(userEntity);
            return entity;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<JournalEntity> getAllJournals()throws Exception {

        return journalDAO.findAll();
    }

    @Override
    public JournalEntity getJournalById(ObjectId id) throws Exception {

        return journalDAO.findById(String.valueOf(id)).orElse(null);

    }

    @Override
    public JournalEntity updateJournals(JournalEntity journalEntity, ObjectId id)throws Exception {


        JournalEntity entryOld= journalDAO.findById(String.valueOf(id)).orElse(null);

        if(entryOld!=null){

            /*if(dto.getName()!=null)entryOld.setName(!entryOld.getName().equalsIgnoreCase(dto.getName())
                    ?dto.getName():entryOld.getName());

            if(dto.getDesc()!=null)entryOld.setDesc(!entryOld.getDesc().equalsIgnoreCase(dto.getDesc())
                    ?dto.getDesc():entryOld.getDesc());*/

            return journalDAO.save(journalEntity);
        }else{
            return null;
        }

    }

    @Override
    public boolean deleteJournal(String id)throws Exception {

        journalDAO.deleteById(id);

        return true;
    }
}

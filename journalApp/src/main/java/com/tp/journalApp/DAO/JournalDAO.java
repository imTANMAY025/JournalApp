package com.tp.journalApp.DAO;

import com.tp.journalApp.entity.JournalEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface JournalDAO extends MongoRepository<JournalEntity,String> {}


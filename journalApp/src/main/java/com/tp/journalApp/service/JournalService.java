package com.tp.journalApp.service;

import com.tp.journalApp.entity.JournalEntity;
import org.bson.types.ObjectId;

import java.util.List;

public interface JournalService {

    public JournalEntity saveJournalData(JournalEntity entity, String username)throws Exception;

    public List<JournalEntity> getAllJournals()throws Exception;

    public JournalEntity getJournalById(ObjectId id)throws Exception;

    public JournalEntity updateJournals(JournalEntity entity, ObjectId id)throws Exception;

    public boolean deleteJournal(String id)throws Exception;
}

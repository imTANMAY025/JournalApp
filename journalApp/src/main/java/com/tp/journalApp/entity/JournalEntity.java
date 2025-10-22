package com.tp.journalApp.entity;


import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "journals")
@Getter
@Setter
public class JournalEntity {

    @Id
    private ObjectId id;
    private String name;
    private String desc;
    private Date date;


}

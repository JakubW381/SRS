package org.example.repo;

import org.example.model.EntryLog;
import org.example.model.User;

import java.util.List;
import java.util.UUID;

public class EntryLogRepo {

    List<EntryLog> entryLogDB;

    public EntryLog createEntry(User user){

        EntryLog entry = new EntryLog(

        );


        entryLogDB.add(entry);

    }
    public void updateEntry(UUID id){

    }
    public List<EntryLog> findByUser(){

    }




}

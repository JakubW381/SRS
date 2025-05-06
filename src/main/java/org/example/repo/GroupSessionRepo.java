package org.example.repo;

import org.example.model.EntryLog;
import org.example.model.GroupSession;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GroupSessionRepo {

    List<GroupSession> groupSessionDB;

    public GroupSessionRepo() {

        //TODO lista przykładowych sesji grupowych, analogicznie do userów

        this.groupSessionDB = List.of();
    }

    public List<GroupSession> findAll(){
        return groupSessionDB;
    }
    public Optional<GroupSession> findById(UUID id){
        return groupSessionDB.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }
    public Optional<GroupSession> findByTrainerId(UUID id){
        return groupSessionDB.stream()
                .filter(s -> s.getTrainer().getId().equals(id))
                .findFirst();
    }
    public void save(GroupSession session){
        Optional<GroupSession> oldSession = groupSessionDB.stream()
                .filter(s -> s.getId().equals(session.getId()))
                .findFirst();
        if (!oldSession.isPresent()){
            addSession(session);
        }
        else{
            deleteSession(session.getId());
            addSession(session);
        }
    }
    public void addSession(GroupSession session){
        groupSessionDB.add(session);
    }
    public void deleteSession(UUID id){
        Optional<GroupSession> session = groupSessionDB.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
        groupSessionDB.remove(session);
    }





}

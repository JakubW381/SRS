package org.example.repo;

import org.example.model.EntryLog;
import org.example.model.GroupSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GroupSessionRepo {

    List<GroupSession> groupSessionDB = new ArrayList<>();

    public GroupSessionRepo() {
        TrainerRepo trainerRepo = new TrainerRepo();

        groupSessionDB.add(new GroupSession(trainerRepo.findAll().get(0), 10));  // Yoga with Anna Kowalska
        groupSessionDB.add(new GroupSession(trainerRepo.findAll().get(1), 12));  // Crossfit with Marek Nowak
        groupSessionDB.add(new GroupSession(trainerRepo.findAll().get(2), 15));  // Zumba with Ewa Wiśniewska
        groupSessionDB.add(new GroupSession(trainerRepo.findAll().get(3), 8));   // Bodybuilding with Krzysztof Krawczyk
        groupSessionDB.add(new GroupSession(trainerRepo.findAll().get(4), 10));  // Dance with Joanna Majewska
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
        deleteSession(session.getId());
        groupSessionDB.add(session);
    }
    public void addSession(GroupSession session){
        groupSessionDB.add(session);
    }
    public void deleteSession(UUID id){
        groupSessionDB.removeIf(session -> session.getId().equals(id));
    }
}

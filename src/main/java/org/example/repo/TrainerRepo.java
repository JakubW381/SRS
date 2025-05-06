package org.example.repo;

import org.example.model.Trainer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TrainerRepo {

    private List<Trainer> trainerDB;

    public TrainerRepo() {

        //TODO lista przykładowych trenerów, analogicznie do userów

        this.trainerDB = List.of();
    }

    public List<Trainer> findAll(){
        return trainerDB;
    }
    public Optional<Trainer> findById(UUID id){
        return trainerDB.stream()
                    .filter(t -> t.getId().equals(id))
                    .findFirst();
    }
    public void addTrainer(Trainer trainer){
        if (!trainerDB.contains(trainer)){
            trainerDB.add(trainer);
        }else {
            System.out.println("Trainer already exists");
        }
    }
    public void removeTrainer(UUID id){
        Optional<Trainer> trainerOpt = trainerDB.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
        if (trainerOpt.isPresent()){
            trainerDB.remove(trainerOpt.get());
        }
        else{
            System.out.println("Trainer doesn't exist");
        }
    }
}

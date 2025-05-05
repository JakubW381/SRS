package org.example.repo;

import org.example.model.EntryLog;
import org.example.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EntryLogRepo {

    List<EntryLog> entryLogDB;

    public EntryLog createEntry(User user){
        EntryLog entry = new EntryLog(user);
        entryLogDB.add(entry);
        return entry;
    }
    public boolean updateEntry(UUID id) {
        Optional<EntryLog> entryOpt = entryLogDB.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
        if (entryOpt.isPresent()) {
            EntryLog entry = entryOpt.get();
            entry.setExitTime();
            return true;
        } else {
            return false;
        }
    }
    public List<EntryLog> findByUser(User user) {
        List<EntryLog> result = new ArrayList<>();
        for (EntryLog entry : entryLogDB) {
            if (entry.getUser().equals(user)) {
                result.add(entry);
            }
        }
        return result;
    }




}

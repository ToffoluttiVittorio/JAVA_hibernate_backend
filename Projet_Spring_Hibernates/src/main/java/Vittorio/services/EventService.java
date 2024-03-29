package Vittorio.services;

import Vittorio.models.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Vittorio.models.Event;
import Vittorio.repositories.EventRepo;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepo repository;

    public List<Event> findAll() {
        return (List<Event>) repository.findAll();
    }

    public void save(Event event) {
        repository.save(event);
    }

    public Optional<Event> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Event event) {
        repository.delete(event);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }





}


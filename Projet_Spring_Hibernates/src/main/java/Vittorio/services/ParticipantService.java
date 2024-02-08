package Vittorio.services;

import Vittorio.models.Event;
import Vittorio.models.Participant;
import Vittorio.repositories.EventRepo;
import Vittorio.repositories.ParticipantRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepo repository;

    @Autowired
    private EventRepo eventrepo;

    public List<Participant> findAll() {
        return (List<Participant>) repository.findAll();
    }

    @Transactional
    public void save(Participant participant) {
        Event event = participant.getEvent();
        if (event != null && event.getId() == null) {
            // Si l'ID de l'événement n'existe pas, enregistrez l'événement avant le participant
            eventrepo.save(event);
        }

        repository.save(participant);
    }
    public Optional<Participant> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }


    public void delete(Participant participant) {
        repository.delete(participant);
    }

    public boolean isEventFull(Long eventId) {
        Optional<Event> eventOptional = eventrepo.findById(eventId);
        return eventOptional.map(event -> event.getParticipants().size() >= event.getNb_participant_max()).orElse(true);
    }



}


package Vittorio.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import Vittorio.models.Participant;

public interface ParticipantRepo extends CrudRepository<Participant, Long> {
}

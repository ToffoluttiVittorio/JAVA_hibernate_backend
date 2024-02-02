package Vittorio.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import Vittorio.models.Event;

@Repository
public interface EventRepo extends CrudRepository<Event, Long> {
}
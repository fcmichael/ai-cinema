package pl.michalkruk.cinema.core.event;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    List<Event> findAll() {
        return eventRepository.findAll();
    }

}

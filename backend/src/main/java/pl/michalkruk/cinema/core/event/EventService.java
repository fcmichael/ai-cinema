package pl.michalkruk.cinema.core.event;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

}

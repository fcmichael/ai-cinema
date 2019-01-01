package pl.michalkruk.cinema.core.event;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.michalkruk.cinema.util.FileService;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final int NUMBER_OF_EVENTS_TO_SHOW = 4;
    private final EventRepository eventRepository;
    private final String imageLocation;

    public EventService(EventRepository eventRepository, @Value("${event.images.location}") String imageLocation) {
        this.eventRepository = eventRepository;
        this.imageLocation = imageLocation;
    }

    EventDTO findEventById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapToDTO(event);
    }

    List<EventDTO> findEvents() {
        return findLastNEventsSortedByEventDate(NUMBER_OF_EVENTS_TO_SHOW).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    List<Event> findLastNEventsSortedByEventDate(int n) {
        return eventRepository.findAll().stream()
                .sorted(Comparator.comparing(Event::getEventDate).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    private EventDTO mapToDTO(Event event) {
        return new EventDTO(
                event.getId(),
                event.getTitle(),
                event.getEventDate().toString(),
                event.getShortDescription(),
                event.getDescription(),
                FileService.encodeImageWithBase64(imageLocation + event.getImageName())
        );
    }
}

package pl.michalkruk.cinema.core.event;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.michalkruk.cinema.util.FileService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventFacade {

    private final EventService eventService;
    private final String imageLocation;

    public EventFacade(EventService eventService, @Value("${event.images.location}") String imageLocation) {
        this.eventService = eventService;
        this.imageLocation = imageLocation;
    }

    EventDTO findEventById(Long id) {
        return mapToDTO(eventService.findById(id));
    }

    List<EventDTO> findAll() {
        return mapToDTO(eventService.findAll());
    }

    private List<EventDTO> mapToDTO(List<Event> events) {
        return events.stream()
                .map(this::mapToDTO)
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

package pl.michalkruk.cinema.core.event;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable(name = "id") Long id){
        EventDTO event = eventService.findEventById(id);
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getEvents() {
        List<EventDTO> events = eventService.findEvents();
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }
}

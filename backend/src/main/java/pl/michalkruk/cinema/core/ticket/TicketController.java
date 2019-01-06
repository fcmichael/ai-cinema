package pl.michalkruk.cinema.core.ticket;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketFacade ticketFacade;

    public TicketController(TicketFacade ticketFacade) {
        this.ticketFacade = ticketFacade;
    }

    @GetMapping("/types")
    public ResponseEntity<List<TicketTypeDTO>> getAllTicketTypes() {
        List<TicketTypeDTO> ticketTypes = ticketFacade.findAllTicketTypes();
        return ResponseEntity.status(HttpStatus.OK).body(ticketTypes);
    }
}

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

    private final TicketTypeService ticketTypeService;

    public TicketController(TicketTypeService ticketTypeService) {
        this.ticketTypeService = ticketTypeService;
    }

    @GetMapping("/types")
    public ResponseEntity<List<TicketTypeDTO>> getAllTicketTypes() {
        List<TicketTypeDTO> ticketTypes = ticketTypeService.findAllTicketTypes();
        return ResponseEntity.status(HttpStatus.OK).body(ticketTypes);
    }
}

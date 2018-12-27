package pl.michalkruk.cinema.core.show;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.michalkruk.cinema.core.show.exception.SeatAlreadyOccupied;

import java.util.Set;

@RequestMapping("/shows")
@RestController
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowDTO> getShow(@PathVariable(name = "id") long id) {
        ShowDTO show = showService.findShowById(id);
        return ResponseEntity.status(HttpStatus.OK).body(show);
    }

    @PostMapping("/{id}/reservations")
    public ResponseEntity<ReservationDTO> makeReservation(@PathVariable(name = "id") long id,
                                                          @RequestBody ReservationForm form) {
        try {
            ReservationDTO reservation = showService.makeReservation(id, form);
            return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
        } catch (SeatAlreadyOccupied e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Przynajmniej jedno miejsce jest już zarezerwowane");
        }
    }

    @GetMapping("{id}/reservedSeats")
    public ResponseEntity<Set<String>> getReservedSeatsForShow(@PathVariable(name = "id") long id) {
        Set<String> reservedSeats = showService.getReservedSeatsForShow(id);
        return ResponseEntity.status(HttpStatus.OK).body(reservedSeats);
    }
}

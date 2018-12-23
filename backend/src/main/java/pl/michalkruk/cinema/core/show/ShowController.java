package pl.michalkruk.cinema.core.show;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/shows")
@RestController
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @RequestMapping("/{id}")
    public ResponseEntity<ShowDTO> getShow(@PathVariable(name = "id") long id) {
        ShowDTO show = showService.findShowById(id);
        return ResponseEntity.status(HttpStatus.OK).body(show);
    }

    @RequestMapping("{id}/reservedSeats")
    public ResponseEntity<List<String>> getReservedSeatsForShow(@PathVariable(name = "id") long id) {
        List<String> reservedSeats = showService.getReservedSeatsForShow(id);
        return ResponseEntity.status(HttpStatus.OK).body(reservedSeats);
    }
}

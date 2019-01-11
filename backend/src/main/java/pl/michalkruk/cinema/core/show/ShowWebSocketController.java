package pl.michalkruk.cinema.core.show;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
public class ShowWebSocketController {

    private ShowFacade showFacade;

    public ShowWebSocketController(ShowFacade showFacade) {
        this.showFacade = showFacade;
    }

    @MessageMapping("/hello/single/{showId}")
    @SendTo("/topic/hi/single/{showId}")
    public TemporarySeatReservationForm getReservedSeat(@DestinationVariable Long showId, TemporarySeatReservationForm seat) {
        showFacade.addTemporaryReservation(showId, seat);
        return seat;
    }

    @MessageMapping("/hello/multi/{showId}")
    @SendTo("/topic/hi/multi/{showId}")
    public Set<String> seatsReleased(@DestinationVariable Long showId, Set<String> seats) {
        showFacade.removeTemporaryReservations(showId, seats);
        return seats;
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class TemporarySeatReservationForm {
    private String name;
    private boolean reserved;
}
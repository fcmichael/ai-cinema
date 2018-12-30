package pl.michalkruk.cinema.core.show;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class ReservationDTO {
    private Long id;
    private String movieTitle;
    private String showDate;
    private String showTime;
    private Set<String> reservedSeats;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}

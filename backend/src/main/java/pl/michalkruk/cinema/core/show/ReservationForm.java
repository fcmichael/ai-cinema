package pl.michalkruk.cinema.core.show;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
class ReservationForm {
    private Set<String> seats;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}

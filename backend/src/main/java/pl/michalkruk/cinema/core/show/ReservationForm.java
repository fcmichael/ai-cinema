package pl.michalkruk.cinema.core.show;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
class ReservationForm {

    @NotNull
    @Size(min = 1)
    private Set<String> seats;

    @NotNull
    @Size(min = 2, max = 64)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 64)
    private String lastName;

    @NotNull
    @Size(min = 9, max = 9)
    private String phoneNumber;
}

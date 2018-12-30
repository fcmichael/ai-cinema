package pl.michalkruk.cinema.core.show;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Show show;

    private LocalDateTime timestamp;

    private String clientsInfo;

    private String firstName;

    private String lastName;

    private String phoneNumber;
}

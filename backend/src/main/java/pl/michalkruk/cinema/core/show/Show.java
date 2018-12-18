package pl.michalkruk.cinema.core.show;

import lombok.*;
import pl.michalkruk.cinema.core.movie.Movie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Show {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Movie movie;

    private LocalDate showDate;

    private LocalTime showTime;
}

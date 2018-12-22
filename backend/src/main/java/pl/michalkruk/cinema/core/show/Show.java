package pl.michalkruk.cinema.core.show;

import lombok.*;
import pl.michalkruk.cinema.core.movie.Movie;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Table(uniqueConstraints = @UniqueConstraint
        (columnNames = {"auditorium_id", "show_date", "show_time"},
                name = "unique_auditorium_show_date_and_show_time"))
public class Show {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium;

    @Column(name = "show_date")
    private LocalDate showDate;

    @Column(name = "show_time")
    private LocalTime showTime;
}

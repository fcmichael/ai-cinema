package pl.michalkruk.cinema.core.show;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.michalkruk.cinema.core.movie.Movie;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findAllByMovieAndShowDateAndShowTimeAfterOrderByShowTimeAsc(Movie movie, LocalDate showDate, LocalTime showTime);
}

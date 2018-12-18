package pl.michalkruk.cinema.core.show;

import org.springframework.stereotype.Service;
import pl.michalkruk.cinema.core.movie.Movie;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ShowService {

    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public List<Show> findShowsByMovieAndDateAndTimeAfter(Movie movie, LocalDate date, LocalTime time) {
        return showRepository.findAllByMovieAndShowDateAndShowTimeAfterOrderByShowTimeAsc(movie, date, time);
    }
}

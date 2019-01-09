package pl.michalkruk.cinema.core.show;

import org.springframework.stereotype.Service;
import pl.michalkruk.cinema.core.movie.Movie;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ShowService {

    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public List<Show> findShowsPossibleToMakingReservationByMovieAndDate(Movie movie, LocalDate date) {
        if (LocalDate.now().isEqual(date)) {
            return showRepository.findAllByMovieAndShowDateAndShowTimeAfterOrderByShowTimeAsc(
                    movie, date, LocalTime.now().plusMinutes(ReservationLimit.MOVIE_RESERVATION_DEADLINE_MINUTES));
        } else {
            return showRepository.findAllByMovieAndShowDateAndShowTimeAfterOrderByShowTimeAsc(movie, date, LocalTime.MIN);
        }
    }

    Show findById(Long id) {
        return showRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}

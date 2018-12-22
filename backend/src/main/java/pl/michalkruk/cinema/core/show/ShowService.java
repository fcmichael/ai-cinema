package pl.michalkruk.cinema.core.show;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.michalkruk.cinema.core.movie.Movie;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ShowService {

    private final ModelMapper modelMapper;
    private final ShowRepository showRepository;

    public ShowService(ModelMapper modelMapper, ShowRepository showRepository) {
        this.modelMapper = modelMapper;
        this.showRepository = showRepository;
    }

    public List<Show> findShowsByMovieAndDateAndTimeAfter(Movie movie, LocalDate date, LocalTime time) {
        return showRepository.findAllByMovieAndShowDateAndShowTimeAfterOrderByShowTimeAsc(movie, date, time);
    }

    ShowDTO findShowById(long id) {
        Show show = showRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(show, ShowDTO.class);
    }
}

package pl.michalkruk.cinema.core.programme;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.michalkruk.cinema.core.movie.Country;
import pl.michalkruk.cinema.core.movie.Genre;
import pl.michalkruk.cinema.core.movie.Movie;
import pl.michalkruk.cinema.core.movie.MovieService;
import pl.michalkruk.cinema.core.show.Show;
import pl.michalkruk.cinema.core.show.ShowService;
import pl.michalkruk.cinema.util.FileService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgrammeService {

    private final MovieService movieService;
    private final ShowService showService;
    private final String imageLocation;

    public ProgrammeService(MovieService movieService, ShowService showService, @Value("${movie.images.location}") String imageLocation) {
        this.movieService = movieService;
        this.showService = showService;
        this.imageLocation = imageLocation;
    }

    List<ProgrammeMovieDTO> findAllByGenreCountryAndReleaseYearAndDate(Genre genre, Country country, String releaseYear, LocalDate date) {
        return movieService.findByGenreCountryAndReleaseYear(genre, country, releaseYear).stream()
                .map(movie -> mapToDTO(movie, findShowsByMovieAndDate(movie, date)))
                .filter(programmeMovieDTO -> !programmeMovieDTO.getShows().isEmpty())
                .collect(Collectors.toList());
    }

    List<Show> findShowsByMovieAndDate(Movie movie, LocalDate date) {
        if (LocalDate.now().isEqual(date)) {
            return showService.findShowsByMovieAndDateAndTimeAfter(movie, date, LocalTime.now());
        } else {
            return showService.findShowsByMovieAndDateAndTimeAfter(movie, date, LocalTime.MIN);
        }
    }

    private ProgrammeMovieDTO mapToDTO(Movie movie, List<Show> shows) {
        return new ProgrammeMovieDTO(movie.getId(),
                movie.getTitle(),
                movie.getGenre().toString(),
                movie.getAgeLimit().toString(),
                movie.getDuration(),
                movie.getReleaseYear(),
                movie.getCountry().toString(),
                movie.getDescription(),
                FileService.encodeImageWithBase64(imageLocation + movie.getImageName()),
                shows.stream().map(this::mapToDTO).collect(Collectors.toList()));
    }

    private ShowDTO mapToDTO(Show show) {
        return new ShowDTO(show.getId(), show.getShowDate().toString(), show.getShowTime().toString());
    }
}

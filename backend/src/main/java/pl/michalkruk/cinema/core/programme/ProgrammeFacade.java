package pl.michalkruk.cinema.core.programme;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.michalkruk.cinema.core.movie.Country;
import pl.michalkruk.cinema.core.movie.Genre;
import pl.michalkruk.cinema.core.movie.Movie;
import pl.michalkruk.cinema.core.movie.MovieService;
import pl.michalkruk.cinema.core.show.Show;
import pl.michalkruk.cinema.core.show.ShowService;
import pl.michalkruk.cinema.util.FileService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProgrammeFacade {

    private final MovieService movieService;
    private final ShowService showService;
    private final FileService fileService;
    private final String movieImageLocation;

    public ProgrammeFacade(MovieService movieService, ShowService showService, FileService fileService,
                           @Value("${movie.images.location}") String movieImageLocation) {
        this.movieService = movieService;
        this.showService = showService;
        this.fileService = fileService;
        this.movieImageLocation = movieImageLocation;
    }

    @Transactional(readOnly = true)
    public List<ProgrammeMovieDTO> findAllNotStartedShowsByGenreCountryAndReleaseYearAndDate(
            Genre genre, Country country, String releaseYear, LocalDate date) {
        return movieService.findByGenreCountryAndReleaseYear(genre, country, releaseYear)
                .stream()
                .map(movie -> mapToDTO(movie, showService.findNotStartedShowsByMovieAndDate(movie, date)))
                .filter(programmeMovieDTO -> !programmeMovieDTO.getShows().isEmpty())
                .collect(Collectors.toList());
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
                fileService.encodeImageWithBase64(movieImageLocation + movie.getImageName()),
                shows.stream().map(this::mapToDTO).collect(Collectors.toList()));
    }

    private ShowDTO mapToDTO(Show show) {
        return new ShowDTO(show.getId(), show.getShowDate().toString(), show.getShowTime().toString());
    }
}

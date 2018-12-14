package pl.michalkruk.cinema.core.programme;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.michalkruk.cinema.core.movie.Country;
import pl.michalkruk.cinema.core.movie.Genre;
import pl.michalkruk.cinema.core.movie.Movie;
import pl.michalkruk.cinema.core.movie.MovieService;
import pl.michalkruk.cinema.util.FileService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgrammeService {

    private final MovieService movieService;
    private final String imageLocation;

    public ProgrammeService(MovieService movieService, @Value("${images.location}") String imageLocation) {
        this.movieService = movieService;
        this.imageLocation = imageLocation;
    }

    List<ProgrammeMovieDTO> findAllCurrentlyPlayedByGenreCountryAndReleaseYear(Genre genre, Country country, String releaseYear) {
        return movieService.findByGenreCountryAndReleaseYear(genre, country, releaseYear).stream()
                .filter(Movie::isCurrentlyPlayed)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ProgrammeMovieDTO mapToDTO(Movie movie) {
        return new ProgrammeMovieDTO(movie.getId(),
                movie.getTitle(),
                movie.getGenre().toString(),
                movie.getAgeLimit().toString(),
                movie.getDuration(),
                movie.getReleaseYear(),
                movie.getCountry().toString(),
                movie.getDescription(),
                FileService.encodeImageWithBase64(imageLocation + movie.getImageName()));
    }
}

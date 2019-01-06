package pl.michalkruk.cinema.core.movie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.michalkruk.cinema.core.programme.ProgrammeMovieDTO;
import pl.michalkruk.cinema.util.FileService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final String imageLocation;

    public MovieService(MovieRepository movieRepository, @Value("${movie.images.location}") String imageLocation) {
        this.movieRepository = movieRepository;
        this.imageLocation = imageLocation;
    }

    public List<MovieDTO> findAllDTO() {
        return findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public List<Movie> findAll(Genre genre, Country country, String releaseYear) {
        return findByGenreCountryAndReleaseYear(genre, country, releaseYear);
    }

    public List<Movie> findByGenreCountryAndReleaseYear(Genre genre, Country country, String releaseYear) {
        return findAll().stream()
                .filter(movie -> genre == null || movie.getGenre().equals(genre))
                .filter(movie -> country == null || movie.getCountry().equals(country))
                .filter(movie -> releaseYear == null || movie.getReleaseYear() == Short.valueOf(releaseYear))
                .collect(Collectors.toList());
    }

    private MovieDTO mapToDTO(Movie movie) {
        return new MovieDTO(movie.getId(),
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

package pl.michalkruk.cinema.core.movie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.michalkruk.cinema.util.FileService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieFacade {

    private final MovieService movieService;
    private final String imageLocation;

    public MovieFacade(MovieService movieService, @Value("${movie.images.location}") String imageLocation) {
        this.movieService = movieService;
        this.imageLocation = imageLocation;
    }

    List<MovieDTO> findAll() {
        return mapToDTO(movieService.findAll());
    }

    private List<MovieDTO> mapToDTO(List<Movie> movies) {
        return movies.stream().map(this::mapToDTO).collect(Collectors.toList());
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

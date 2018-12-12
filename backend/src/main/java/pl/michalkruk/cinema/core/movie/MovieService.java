package pl.michalkruk.cinema.core.movie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.michalkruk.cinema.util.FileService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final String imageLocation;

    public MovieService(MovieRepository movieRepository, @Value("${images.location}") String imageLocation) {
        this.movieRepository = movieRepository;
        this.imageLocation = imageLocation;
    }

    public List<MovieDTO> findAll() {
        return movieRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private MovieDTO mapToDTO(Movie movie) {
        return new MovieDTO(movie.getTitle(), movie.getGenre().toString(), FileService.encodeImageWithBase64(imageLocation + movie.getImageName()));
    }
}

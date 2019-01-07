package pl.michalkruk.cinema.core.movie;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findByGenreCountryAndReleaseYear(Genre genre, Country country, String releaseYear) {
        return findAll().stream()
                .filter(movie -> genre == null || movie.getGenre().equals(genre))
                .filter(movie -> country == null || movie.getCountry().equals(country))
                .filter(movie -> releaseYear == null || movie.getReleaseYear() == Short.valueOf(releaseYear))
                .collect(Collectors.toList());
    }

    Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    List<Movie> findAll() {
        return movieRepository.findAll();
    }
}

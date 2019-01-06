package pl.michalkruk.cinema.core.movie;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public List<Movie> findByGenreCountryAndReleaseYear(Genre genre, Country country, String releaseYear) {
        return findAll().stream()
                .filter(movie -> genre == null || movie.getGenre().equals(genre))
                .filter(movie -> country == null || movie.getCountry().equals(country))
                .filter(movie -> releaseYear == null || movie.getReleaseYear() == Short.valueOf(releaseYear))
                .collect(Collectors.toList());
    }

}

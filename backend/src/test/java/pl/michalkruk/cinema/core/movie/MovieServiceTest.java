package pl.michalkruk.cinema.core.movie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.michalkruk.cinema.core.movie.Country.GERMANY;
import static pl.michalkruk.cinema.core.movie.Country.USA;
import static pl.michalkruk.cinema.core.movie.Genre.*;

public class MovieServiceTest {

    private MovieRepository movieRepository;
    private MovieService movieService;

    private List<Movie> movies;

    @Before
    public void init() {
        this.movieRepository = mock(MovieRepository.class);
        this.movieService = new MovieService(movieRepository, "");
        this.movies = new ArrayList<>();

        this.movies.add(Movie.builder().id(1L).title("A").genre(ANIMOWANY).releaseYear((short) 2018).country(USA).build());
        this.movies.add(Movie.builder().id(2L).title("B").genre(DRAMAT).releaseYear((short) 2005).country(GERMANY).build());
        this.movies.add(Movie.builder().id(3L).title("C").genre(GANGSTERSKI).releaseYear((short) 2003).country(GERMANY).build());
        this.movies.add(Movie.builder().id(4L).title("D").genre(ANIMOWANY).releaseYear((short) 1994).country(GERMANY).build());
        this.movies.add(Movie.builder().id(5L).title("E").genre(ANIMOWANY).releaseYear((short) 1994).country(GERMANY).build());
        this.movies.add(Movie.builder().id(6L).title("F").genre(DRAMAT).releaseYear((short) 2006).country(USA).build());
    }

    @Test
    public void should_find_all_filtered_by_genre() {
        // given
        when(movieRepository.findAll()).thenReturn(movies);

        // when
        List<Movie> filteredByGenre = movieService.
                findByGenreCountryAndReleaseYear(ANIMOWANY, null, null);

        // then
        Assert.assertEquals(3, filteredByGenre.size());
        Assert.assertTrue(filteredByGenre.contains(movies.get(0)));
        Assert.assertTrue(filteredByGenre.contains(movies.get(3)));
        Assert.assertTrue(filteredByGenre.contains(movies.get(4)));
    }

    @Test
    public void should_find_all_filtered_by_country() {
        // given
        when(movieRepository.findAll()).thenReturn(movies);

        // when
        List<Movie> filteredByGenre = movieService.
                findByGenreCountryAndReleaseYear(null, USA, null);

        // then
        Assert.assertEquals(2, filteredByGenre.size());
        Assert.assertTrue(filteredByGenre.contains(movies.get(0)));
        Assert.assertTrue(filteredByGenre.contains(movies.get(5)));
    }

    @Test
    public void should_find_all_filtered_by_releaseYear() {
        // given
        when(movieRepository.findAll()).thenReturn(movies);

        // when
        List<Movie> filteredByGenre = movieService.
                findByGenreCountryAndReleaseYear(null, null, "2018");

        // then
        Assert.assertEquals(1, filteredByGenre.size());
        Assert.assertTrue(filteredByGenre.contains(movies.get(0)));
    }

    @Test
    public void should_find_all_filtered_by_genre_country_and_releaseYear() {
        // given
        when(movieRepository.findAll()).thenReturn(movies);

        // when
        List<Movie> filteredByGenre = movieService.
                findByGenreCountryAndReleaseYear(ANIMOWANY, GERMANY, "1994");

        // then
        Assert.assertEquals(2, filteredByGenre.size());
        Assert.assertTrue(filteredByGenre.contains(movies.get(3)));
        Assert.assertTrue(filteredByGenre.contains(movies.get(4)));
    }
}

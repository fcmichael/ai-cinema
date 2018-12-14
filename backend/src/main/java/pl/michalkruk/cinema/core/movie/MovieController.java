package pl.michalkruk.cinema.core.movie;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> findAll(@RequestParam(value = "genre", required = false) Genre genre,
                                                  @RequestParam(value = "country", required = false) Country country,
                                                  @RequestParam(value = "releaseYear", required = false) String releaseYear) {
        List<MovieDTO> all = movieService.findAll(genre, country, releaseYear);
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }
}

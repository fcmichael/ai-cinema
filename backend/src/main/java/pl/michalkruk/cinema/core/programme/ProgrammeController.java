package pl.michalkruk.cinema.core.programme;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.michalkruk.cinema.core.movie.Country;
import pl.michalkruk.cinema.core.movie.Genre;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/programme")
public class ProgrammeController {

    private final ProgrammeService programmeService;

    public ProgrammeController(ProgrammeService programmeService) {
        this.programmeService = programmeService;
    }

    @GetMapping
    public ResponseEntity<List<ProgrammeMovieDTO>> findAllCurrentlyPlayed(
            @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(value = "genre", required = false) Genre genre,
            @RequestParam(value = "country", required = false) Country country,
            @RequestParam(value = "releaseYear", required = false) String releaseYear) {
        List<ProgrammeMovieDTO> all = programmeService.findAllByGenreCountryAndReleaseYearAndDate(genre, country, releaseYear, date);
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }
}

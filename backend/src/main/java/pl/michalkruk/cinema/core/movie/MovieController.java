package pl.michalkruk.cinema.core.movie;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieFacade movieFacade;

    public MovieController(MovieFacade movieFacade) {
        this.movieFacade = movieFacade;
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<MovieDTO> all = movieFacade.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> addMovie(@RequestPart(value = "form") String formJSON,
                                             @RequestPart(value = "file", required = false) MultipartFile file) {

        Gson gson = new Gson();
        MovieForm form = gson.fromJson(formJSON, MovieForm.class);

        MovieDTO movie = movieFacade.addMovie(form, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(movie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getById(@PathVariable(name = "id") Long id) {
        MovieDTO movie = movieFacade.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> editMovie(@PathVariable(name = "id") Long id,
                                              @RequestBody MovieForm form) {
        MovieDTO movie = movieFacade.editMovie(id, form);
        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }
}

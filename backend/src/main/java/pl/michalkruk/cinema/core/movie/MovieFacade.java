package pl.michalkruk.cinema.core.movie;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.michalkruk.cinema.util.FileService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieFacade {

    private final MovieService movieService;
    private final FileService fileService;
    private final ModelMapper modelMapper;
    private final String imageLocation;

    public MovieFacade(MovieService movieService, FileService fileService, ModelMapper modelMapper,
                       @Value("${movie.images.location}") String imageLocation) {
        this.movieService = movieService;
        this.fileService = fileService;
        this.modelMapper = modelMapper;
        this.imageLocation = imageLocation;
    }

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id) {
        return mapToDTO(movieService.findById(id));
    }

    @Transactional(readOnly = true)
    public List<MovieDTO> findAll() {
        return mapToDTO(movieService.findAll());
    }

    @Transactional
    public MovieDTO editMovie(Long id, MovieForm form) {
        Movie movie = movieService.findById(id);
        movie.setTitle(form.getTitle());
        movie.setGenre(form.getGenre());
        movie.setAgeLimit(form.getAgeLimit());
        movie.setDuration(form.getDuration());
        movie.setReleaseYear(form.getReleaseYear());
        movie.setCountry(form.getCountry());
        movie.setDescription(form.getDescription());

        return mapToDTO(movie);
    }

    @Transactional
    public MovieDTO addMovie(MovieForm form, MultipartFile file) {
        Movie movie = modelMapper.map(form, Movie.class);

        if (file != null) {
            movie.setImageName(fileService.storeFile(imageLocation, file));
        }

        movieService.save(movie);

        return mapToDTO(movie);
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
                fileService.encodeImageWithBase64(imageLocation + movie.getImageName()));
    }
}

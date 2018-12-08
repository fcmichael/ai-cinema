package pl.michalkruk.cinema.core.movie;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public MovieService(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }

    public List<MovieDTO> findAll() {
        return movieRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private MovieDTO mapToDTO(Movie movie) {
        return modelMapper.map(movie, MovieDTO.class);
    }
}

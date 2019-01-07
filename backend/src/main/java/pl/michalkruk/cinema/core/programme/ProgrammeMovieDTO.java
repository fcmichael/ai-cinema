package pl.michalkruk.cinema.core.programme;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
class ProgrammeMovieDTO {
    private Long id;
    private String title;
    private String genre;
    private String ageLimit;
    private Short duration;
    private Short releaseYear;
    private String country;
    private String description;
    private String image;
    private List<ShowDTO> shows;
}

@Data
@AllArgsConstructor
class ShowDTO {
    private Long id;
    private String showDate;
    private String showTime;
}

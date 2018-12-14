package pl.michalkruk.cinema.core.movie;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class MovieDTO {
    private long id;
    private String title;
    private String genre;
    private String ageLimit;
    private short duration;
    private short releaseYear;
    private String country;
    private String description;
    private String image;
}

package pl.michalkruk.cinema.core.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class MovieForm {
    private String title;
    private Genre genre;
    private AgeLimit ageLimit;
    private short duration;
    private short releaseYear;
    private Country country;
    private String description;
}

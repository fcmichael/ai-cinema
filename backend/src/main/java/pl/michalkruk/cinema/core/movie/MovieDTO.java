package pl.michalkruk.cinema.core.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class MovieDTO {
    private Long id;
    private String title;
    private String genre;
    private String ageLimit;
    private Short duration;
    private Short releaseYear;
    private String country;
    private String description;
    private String image;
}

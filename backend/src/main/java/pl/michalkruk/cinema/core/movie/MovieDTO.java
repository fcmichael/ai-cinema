package pl.michalkruk.cinema.core.movie;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class MovieDTO {
    private String title;
    private String genre;
    private String image;
}

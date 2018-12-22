package pl.michalkruk.cinema.core.show;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class ShowDTO {
    private Long id;
    private String movieTitle;
    private String auditoriumName;
    private String showDate;
    private String showTime;
    private Short auditoriumRows;
    private Short auditoriumColumns;
}

package pl.michalkruk.cinema.core.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class EventDTO {
    private Long id;
    private String title;
    private String eventDate;
    private String shortDescription;
    private String description;
    private String image;
}

package pl.michalkruk.cinema.core.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
class Event {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private LocalDate eventDate;

    @Column(length = 800)
    private String shortDescription;

    @Column(length = 2000)
    private String description;

    private String imageName;
}

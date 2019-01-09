package pl.michalkruk.cinema.core.movie;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private AgeLimit ageLimit;

    private short duration;

    private short releaseYear;

    @Enumerated(EnumType.STRING)
    private Country country;

    @Column(length = 2000)
    private String description;

    private String imageName;
}

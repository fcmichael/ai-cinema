package pl.michalkruk.cinema.core.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "tytul")
    private String title;

    @Column(name = "gatunek")
    @Enumerated(EnumType.STRING)
    private Genre genre;
}

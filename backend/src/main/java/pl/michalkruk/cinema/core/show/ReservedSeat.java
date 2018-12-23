package pl.michalkruk.cinema.core.show;

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
public class ReservedSeat {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Show show;

    @Column(length = 3)
    private String seat;
}
